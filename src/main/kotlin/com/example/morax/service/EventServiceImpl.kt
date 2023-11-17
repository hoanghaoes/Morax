package com.example.morax.service

import com.example.morax.model.Event
import com.example.morax.model.EventReq
import com.example.morax.model.EventResp
import com.example.morax.repo.EventRepoImpl
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/events")
class EventServiceImpl(val eventRepo: EventRepoImpl): EventService {
    override fun addEvents(eventReq: EventReq): Mono<EventResp> {
        val newEvent = Event(eventReq)
        return Mono.just(EventResp(eventRepo.addEvent(newEvent)))
    }

    override fun listEvent(): Mono<List<EventResp>> {
        val events = eventRepo.getEvents().map { event -> EventResp(event) }
        return Mono.just(events)
    }

    override fun getEventById(id: String): Mono<EventResp> {
        return Mono.just(EventResp(eventRepo.eventById(id)))
    }
}