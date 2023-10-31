package com.example.morax.repo

import com.example.morax.model.Token
import org.springframework.stereotype.Component

@Component
class TokenRepoImpl: TokenRepo {
    override fun findAllValidTokenByUser(userId: String): List<Token> {
        TODO("Not yet implemented")
    }

    override fun findByToken(token: String): Token? {
        TODO("Not yet implemented")
    }

    override fun addToken(token: Token): Token {
        TODO("Not yet implemented")
    }

    override fun updateToken(token: Token): Token {
        TODO("Not yet implemented")
    }


}