package com.example.morax.controller

import com.example.morax.model.*
import com.example.morax.service.UserServiceImpl
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/user")
class UserController(val userService: UserServiceImpl) {
    @PostMapping("/register")
    fun createUser(@RequestBody userReq: UserReq): Mono<UserResp> {
        return userService.createUser(userReq)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginReq: LoginReq): Mono<LoginResp> {
        return userService.authenticate(loginReq)
    }

    @GetMapping
    fun getCurrentUser(): Mono<UserResp> {
        return userService.getCurrentUser()
    }

    @PostMapping("/refresh-token")
    fun refreshToken() {

    }
}