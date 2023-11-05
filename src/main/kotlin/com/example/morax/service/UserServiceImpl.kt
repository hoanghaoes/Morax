package com.example.morax.service

import com.example.morax.config.security.JwtProvider
import com.example.morax.model.*
import com.example.morax.repo.TokenRepoImpl
import com.example.morax.repo.UserRepoImpl
import com.example.morax.util.MoraxUtils
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    val userRepo: UserRepoImpl,
    val tokenRepo: TokenRepoImpl,
//    val authenticationManager: AuthenticationManager,
    val jwtProvider: JwtProvider
): UserService {
    override fun createUser(@RequestBody userReq: UserReq): Mono<UserResp> {
        val user = userRepo.addUser(userReq)
        return Mono.just(UserResp(user))
    }

    override fun authenticate(loginReq: LoginReq): Mono<LoginResp> {
//        authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(
//                loginReq.email,
//                loginReq.password
//            )
//        )
        val user = userRepo.findUserByEmail(loginReq.email)
        val jwtToken = jwtProvider.generateToken(user)
        val refreshToken = jwtProvider.generateRefreshToken(user)
        saveToken(user, jwtToken)

        return Mono.just(LoginResp(UserResp(user), jwtToken, refreshToken))
    }

    fun saveToken(user: User, jwtToken: String) {
        val tokenId = MoraxUtils.newUUID()
        val token = Token(id = tokenId, token = jwtToken, userId = user.id)
        tokenRepo.addToken(token)
    }
}