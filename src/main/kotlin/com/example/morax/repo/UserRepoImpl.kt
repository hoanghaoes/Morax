package com.example.morax.repo

import com.example.morax.model.User
import com.example.morax.model.UserReq
import com.mongodb.DuplicateKeyException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.insert
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.server.ResponseStatusException

@Component
class UserRepoImpl(
    val mongoTemplate: MongoTemplate,
    val transactionTemplate: TransactionTemplate,
    @Value("\${data.mongodb.table.user}") val userCol: String
): UserRepo {
    override fun addUser(userReq: UserReq): User {
        val newUser = User.newUser(userReq)
        return transactionTemplate.execute { status ->
            try {
                mongoTemplate.insert(newUser, userCol)
            } catch (e: DuplicateKeyException) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User information is already exist"
                )
            }
            return@execute newUser
        }!!
    }

    override fun updateUser(userReq: UserReq): User {
        TODO("Not yet implemented")
    }

    override fun findUserById(id: String): User {
        TODO("Not yet implemented")
    }

    override fun findUserByEmail(email: String): User {
        TODO("Not yet implemented")
    }

    override fun findUserByUserName(username: String): User {
        TODO("Not yet implemented")
    }

    override fun findUserByUsernameStartWith(username: String): List<User> {
        TODO("Not yet implemented")
    }
}