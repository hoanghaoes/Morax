package com.example.morax.controller

import com.example.morax.model.EventReq
import com.example.morax.model.EventResp
import com.example.morax.service.EventServiceImpl
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/events")
class EventController(val eventService: EventServiceImpl) {

    @PostMapping("", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun createEvent(@ModelAttribute eventReq: EventReq): Mono<EventResp> {
        return eventService.addEvents(eventReq)
    }
    @GetMapping
    fun listEvent(): Mono<List<EventResp>> {
        return eventService.listEvent()
    }

    @GetMapping("/{id}")
    fun getEventById(@PathVariable("id") eventId: String): Mono<EventResp> {
        return eventService.getEventById(eventId)
    }
}