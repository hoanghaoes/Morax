package com.example.morax.repo

import com.example.morax.model.Event
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.server.ResponseStatusException

@Component
class EventRepoImpl(
    val mongoTemplate: MongoTemplate,
    val transactionTemplate: TransactionTemplate,
    @Value("\${data.mongodb.table.events}") val eventsCol: String
) : EventRepo {
    override fun addEvent(event: Event): Event {
        return mongoTemplate.save(event, eventsCol)
    }

    override fun getEvents(): List<Event> {
        return mongoTemplate.findAll(Event::class.java, eventsCol)
    }

    override fun eventById(id: String): Event {
        return mongoTemplate.findById(id, Event::class.java, eventsCol) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Cannot find any event with id $id"
        )
    }
}