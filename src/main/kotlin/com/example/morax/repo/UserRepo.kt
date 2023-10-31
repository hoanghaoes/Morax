package com.example.morax.repo

import com.example.morax.model.User
import com.example.morax.model.UserReq

interface UserRepo {
    fun addUser(userReq: UserReq): User
    fun updateUser(userReq: UserReq): User
    fun findUserById(id: String): User
    fun findUserByEmail(email: String): User
    fun findUserByUserName(username: String): User
    fun findUserByUsernameStartWith(username: String): List<User>
}