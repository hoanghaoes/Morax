package com.example.morax.model

data class UserResp(
    val id: String = "",
    val username: String = "",
    val displayName: String = "",
    val email: String = ""
){
    constructor(user: User) : this(
        id = user.id,
        username = user.username,
        email = user.email,
        displayName = user.displayName
    )
}


data class UserReq(
    val username: String,
    val displayName: String,
    val email: String,
    val password: String,
    val rePassword: String
)

data class LoginReq(
    val username: String,
    val password: String
)

data class LoginResp(
    val user: UserResp,
    val accessKey: String,
)