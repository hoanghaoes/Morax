package com.example.morax.config.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils.hasText
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException
import kotlin.Exception
import kotlin.String

@Component
class JwtAuthFilter(
    val tokenProvider: JwtProvider,
    val userDetailsService: JwtUserDetailsService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.servletPath.contains("/api/v1/register") || request.servletPath.contains("/api/v1/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return;
        }

        try {
            val jwt = getJwtFromRequest(request)
            if (hasText(jwt) && tokenProvider.validateToken(jwt!!)) {
                setAuthenticateForToken(request, jwt)
            }

        } catch (ex: Exception) {
            val log: Logger = LoggerFactory.getLogger(JwtAuthFilter::class.java)
            log.error("failed on set user authentication", ex)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot set user authentication")
        }
        filterChain.doFilter(request, response)
    }

    private fun setAuthenticateForToken(request: HttpServletRequest, jwt: String) {
        val username = tokenProvider.getUsernameFromToken(jwt)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, null,
            userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        println(SecurityContextHolder.getContext())
        SecurityContextHolder.getContext().authentication = authentication
        println(SecurityContextHolder.getContext())
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

}