package com.example.morax.service

import com.example.morax.model.LoginResp
import com.example.morax.model.UserReq
import com.example.morax.model.UserResp
import com.example.morax.repo.UserRepo
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono


@Service
class UserServiceImpl(val userRepo: UserRepo): UserService {
    override fun createUser(@RequestBody userReq: UserReq): Mono<UserResp> {
        val user = userRepo.addUser(userReq)
        return Mono.just(UserResp(user))
    }

    override fun login(): LoginResp {
        TODO("Not yet implemented")
    }
}