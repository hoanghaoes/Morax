package com.example.morax.service

import com.example.morax.model.Artifact
import com.example.morax.model.ArtifactReq
import com.example.morax.model.ArtifactResp
import com.example.morax.repo.ArtifactRepoImpl
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ArtifactServiceImpl(val artifactRepo: ArtifactRepoImpl): ArtifactService {
    override fun addArtifact(artifactReq: ArtifactReq): Mono<ArtifactResp> {
        val newArtifact = Artifact(artifactReq)
        val artifactResp = ArtifactResp(artifactRepo.addArtifact(newArtifact))
        return Mono.just(artifactResp)
    }

    override fun updateArtifact(artifactReq: ArtifactReq, id: String): Mono<ArtifactResp> {
        val artifactResp = ArtifactResp(artifactRepo.updateArtifact(artifactReq, id))
        return Mono.just(artifactResp)
    }

    override fun listArtifact(searchStr: String?): Mono<List<ArtifactResp>> {
        val artifacts = artifactRepo.listArtifact().map { artifact -> ArtifactResp(artifact) }
        return Mono.just(artifacts)
    }
}