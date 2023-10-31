package com.example.morax.controller

import com.example.morax.model.LoginResp
import com.example.morax.model.UserReq
import com.example.morax.model.UserResp
import com.example.morax.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/user")
class UserController(val userService: UserService) {
    @PostMapping("/register")
    fun createUser(@RequestBody userReq: UserReq): Mono<UserResp> {
        return userService.createUser(userReq)
    }

    @GetMapping("/login")
    fun login(): LoginResp {
        return TODO("Provide the return value")
    }
}