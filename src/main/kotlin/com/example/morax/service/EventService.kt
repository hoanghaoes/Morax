package com.example.morax.service

import com.example.morax.model.EventReq
import com.example.morax.model.EventResp
import reactor.core.publisher.Mono

interface EventService {
    fun addEvents(eventReq: EventReq): Mono<EventResp>
    fun listEvent(): Mono<List<EventResp>>
    fun getEventById(id: String): Mono<EventResp>
}