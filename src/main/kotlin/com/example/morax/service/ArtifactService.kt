package com.example.morax.service

import com.example.morax.model.ArtifactReq
import com.example.morax.model.ArtifactResp
import reactor.core.publisher.Mono

interface ArtifactService {
    fun addArtifact(artifactReq: ArtifactReq): Mono<ArtifactResp>
    fun updateArtifact(artifactReq: ArtifactReq, id: String): Mono<ArtifactResp>
    fun listArtifact(searchStr: String?): Mono<List<ArtifactResp>>

}