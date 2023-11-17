package com.example.morax.repo

import com.example.morax.model.Event
import com.example.morax.model.EventReq

interface EventRepo {
    fun addEvent(event: Event): Event
    fun getEvents(): List<Event>
    fun eventById(id: String): Event
}