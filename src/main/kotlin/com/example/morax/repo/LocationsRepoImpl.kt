package com.example.morax.repo

import com.example.morax.model.Location
import com.example.morax.model.LocationReq
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.server.ResponseStatusException

@Component
class LocationsRepoImpl(
    val mongoTemplate: MongoTemplate,
    val transactionTemplate: TransactionTemplate,
    @Value("\${data.mongodb.table.locations}") val locationCol: String
) : LocationsRepo {
    override fun addLocation(location: Location): Location {
        return mongoTemplate.save(location, locationCol)
    }

    override fun updateLocation(locationReq: LocationReq, id: String): Location {
        return transactionTemplate.execute { _ ->
            val location = locationById(id)
            val updatedLocation = location.update(locationReq)
            return@execute mongoTemplate.save(updatedLocation, locationCol)
        } ?: throw ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Cannot update location now. Please try again"
        )
    }

    override fun getLocations(): List<Location> {
        return mongoTemplate.findAll<Location>(locationCol)
    }

    override fun locationById(id: String): Location {
        return mongoTemplate.findById(id, Location::class.java, locationCol) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Cannot find location with id $id"
        )
    }

    override fun getLocations(name: String?): List<Location> {
        var locations = getLocations()
        if (name != null) locations = locations.filter { location -> location.name.contains(name) }
        return locations
    }
}