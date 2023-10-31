package com.example.morax.service

import com.example.morax.model.LoginResp
import com.example.morax.model.UserReq
import com.example.morax.model.UserResp
import reactor.core.publisher.Mono

interface UserService {
    fun createUser(userReq: UserReq): Mono<UserResp>
    fun login(): LoginResp
}