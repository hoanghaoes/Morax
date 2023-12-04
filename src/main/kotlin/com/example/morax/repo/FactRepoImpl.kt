package com.example.morax.repo

import com.example.morax.model.Fact
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.server.ResponseStatusException

class FactRepoImpl(
    val mongoTemplate: MongoTemplate,
    val transactionTemplate: TransactionTemplate,
    @Value("\${data.mongodb.table.facts}") val factCol: String
): FactRepo {
    override fun addFact(fact: Fact): Fact {
        return mongoTemplate.save(fact, factCol)
    }

    override fun getAllFacts(): List<Fact> {
        return mongoTemplate.findAll(Fact::class.java, factCol)
    }

    override fun getFactById(id: String): Fact {
        return mongoTemplate.findById(id, Fact::class.java, factCol) ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find any fact with id $id")
    }

    override fun updateFact(updatedFact: Fact): Fact {
        return mongoTemplate.save(updatedFact)
    }

    override fun deleteFact(id: String) {
        TODO("Not yet implemented")
    }
}