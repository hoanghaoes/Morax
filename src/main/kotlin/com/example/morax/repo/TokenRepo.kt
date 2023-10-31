package com.example.morax.repo

import com.example.morax.model.Token

interface TokenRepo {
    fun findAllValidTokenByUser(userId: String): List<Token>
    fun findByToken(token: String): Token?
    fun addToken(token: Token): Token
    fun updateToken(token: Token): Token
}