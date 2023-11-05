package com.example.morax.controller

import com.example.morax.model.*
import com.example.morax.service.UserService
import com.example.morax.service.UserServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/user")
class UserController(val userService: UserServiceImpl) {
    @PostMapping("/register")
    fun createUser(@RequestBody userReq: UserReq): String {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "abcd")
        return "userService.createUser(userReq)"
    }

    @GetMapping("/login")
    fun login(): Mono<LoginResp> {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Test error")
        return Mono.just(LoginResp(UserResp("", "", "",  ""), "", ""))
    }

    @PostMapping("/refresh-token")
    fun refreshToken() {

    }
}