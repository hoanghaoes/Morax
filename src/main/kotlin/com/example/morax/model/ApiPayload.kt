package com.example.morax.model

import org.bson.types.Binary
import org.springframework.web.multipart.MultipartFile

data class UserResp(
    val id: String = "",
    val username: String = "",
    val displayName: String = "",
    val email: String = ""
){
    constructor(user: User) : this(
        id = user.id,
        username = user.username,
        email = user.email,
        displayName = user.displayName
    )
}


data class UserReq(
    val username: String,
    val displayName: String,
    val email: String,
    var password: String,
    var rePassword: String
)

data class LoginReq(
    val email: String,
    val password: String
)

data class LoginResp(
    val user: UserResp,
    val accessKey: String,
    val refreshKey: String,
)

data class LocationReq(
    val name: String,
    val nameInMap: String,
    val latitude: Double,
    val longitude: Double,
    val image: MultipartFile,
    val description: String
)

data class LocationResp(
    val id: String,
    val name: String,
    val nameInMap: String,
    val latitude: Double,
    val longitude: Double,
    val image: Binary,
    val description: String,
    var artifacts: List<Artifact>
) {
    constructor(location: Location): this(
        location.id,
        location.name,
        location.nameInMap,
        location.latitude,
        location.longitude,
        location.image,
        location.description,
        listOf()
    )
}

data class ArtifactResp(
    val id: String,
    val name: String,
    val time: String,
    val locationId: String,
    val image: Binary,
    val description: String
) {
    constructor(artifact: Artifact): this(
        artifact.id,
        artifact.name,
        artifact.time,
        artifact.locationId,
        artifact.image,
        artifact.description
    )
}


data class ArtifactReq(
    val name: String,
    val time: String,
    val image: MultipartFile,
    val description: String
)

data class EventReq(
    val eventName: String,
    val time: String,
    val address: String,
    val image: MultipartFile,
)

data class EventResp(
    val id: String,
    val eventName: String,
    val time: String,
    val address: String,
    val image: Binary,
) {
    constructor(event: Event): this(
        event.id,
        eventName = event.eventName,
        time = event.time,
        address = event.address,
        image = event.image
    )
}

