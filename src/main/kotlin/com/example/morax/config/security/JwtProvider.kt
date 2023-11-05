package com.example.morax.config.security

import com.example.morax.model.User
import com.example.morax.model.UserResp
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.klogging.NoCoLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.function.Function

@Component
class JwtProvider(
    @Value("\${application.security.jwt.secret-key}") private val secretKey: String,
    @Value("\${application.security.jwt.refresh-token.expiration}") private val refreshExpiration: Long,
    @Value("\${application.security.jwt.expiration}") private val tokenExpiration: Long,
): NoCoLogging {
    private fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getUsernameFromToken(token: String): String {
        return getClaimsFromToken(token, Claims::getSubject)
    }

    fun generateRefreshToken(
        user: User
    ): String {
        return doGenerateToken(HashMap(), user.username, refreshExpiration)
    }

    fun <T> getClaimsFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey()).build()
            .parseClaimsJws(token).body
    }

    fun generateToken(user: User): String {
        val claims: MutableMap<String, Any> = HashMap()
        val userResp = UserResp(user)
        claims["user"] = userResp
        return doGenerateToken(claims, user.username, tokenExpiration)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String, expiration: Long): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
            return true
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }
}