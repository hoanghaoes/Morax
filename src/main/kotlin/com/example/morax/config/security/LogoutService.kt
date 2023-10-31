package com.example.morax.config.security

import com.example.morax.repo.TokenRepo
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service


@Service
class LogoutService(private val tokenRepo: TokenRepo) : LogoutHandler {
    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt: String = authHeader.substring(7)
        val storedToken = tokenRepo.findByToken(jwt)
        if (storedToken != null) {
            storedToken.expired = true
            storedToken.revoked = true
            tokenRepo.updateToken(storedToken)
            SecurityContextHolder.clearContext()
        }
    }
}