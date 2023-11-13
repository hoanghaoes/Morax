package com.example.morax.controller

import com.example.morax.model.*
import com.example.morax.service.ArtifactServiceImpl
import com.example.morax.service.LocationsServiceImpl
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/locations")
class LocationArtifactController(
    val locationsService: LocationsServiceImpl,
    val artifactService: ArtifactServiceImpl
    ) {
    @PostMapping("", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun createLocation(@ModelAttribute locationReq: LocationReq): Mono<LocationResp> {
        return locationsService.createLocation(locationReq)
    }

    @PutMapping("/{id}", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun updateLocation(
        @ModelAttribute locationReq: LocationReq,
        @PathVariable id: String)
    : Mono<LocationResp> {
        return locationsService.updateLocation(locationReq, id)
    }

    @GetMapping
    fun searchLocation(@RequestParam(required = false) name: String?): Mono<List<LocationResp>> {
        return locationsService.locationByName(name)
    }

    @GetMapping("/{id}")
    fun locationDetail(@PathVariable id: String): Mono<LocationResp> {
        return locationsService.locationWithId(id)
    }

    @PostMapping("/{id}/artifacts")
    fun addArtifact(@PathVariable("id") locationId: String, @RequestBody artifactReq: ArtifactReq): Mono<ArtifactResp> {
        return artifactService.addArtifact(artifactReq)
    }

    @PutMapping("/{locationId}/artifacts/{artifactId}")
    fun updateArtifact(
        @PathVariable("locationId") locationId: String,
        @PathVariable("artifactId") artifactId: String,
        @RequestBody artifactReq: ArtifactReq): Mono<ArtifactResp> {
        return artifactService.updateArtifact(artifactReq, artifactId)
    }

    @GetMapping("/{locationId}/artifacts")
    fun searchArtifact(@RequestParam(required = false) searchText: String): Mono<List<ArtifactResp>> {
        return artifactService.listArtifact(searchText)
    }
}