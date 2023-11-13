package com.example.morax.repo

import com.example.morax.model.Token
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate

@Component
class TokenRepoImpl(
    @Value("\${data.mongodb.table.token}") val tokenCol: String,
    val mongoTemplate: MongoTemplate,
    val transactionTemplate: TransactionTemplate,
): TokenRepo {
    override fun findAllValidTokenByUser(userId: String): List<Token> {
        TODO("Not yet implemented")
    }

    override fun findByToken(token: String): Token {
        TODO("Not yet implemented")
    }

    override fun addToken(token: Token): Token {
        return mongoTemplate.save(token, tokenCol)
    }

    override fun updateToken(token: Token): Token {
        TODO("Not yet implemented")
    }


}