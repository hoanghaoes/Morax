package com.example.morax.repo

import com.example.morax.model.Location
import com.example.morax.model.LocationReq

interface LocationsRepo {
    fun addLocation(location: Location): Location
    fun updateLocation(locationReq: LocationReq, id: String): Location
    fun getLocations(): List<Location>
    fun locationById(id: String): Location
    fun getLocations(name: String?): List<Location>
}