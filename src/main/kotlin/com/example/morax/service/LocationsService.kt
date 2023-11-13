package com.example.morax.service

import com.example.morax.model.LocationReq
import com.example.morax.model.LocationResp
import reactor.core.publisher.Mono

interface LocationsService {
    fun createLocation(locationReq: LocationReq): Mono<LocationResp>
    fun updateLocation(locationReq: LocationReq, id: String): Mono<LocationResp>
    fun getLocations(): Mono<List<LocationResp>>
    fun locationWithId(id: String): Mono<LocationResp>
    fun locationByName(name: String?): Mono<List<LocationResp>>
}