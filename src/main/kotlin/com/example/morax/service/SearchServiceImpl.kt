package com.example.morax.service

import com.example.morax.model.Artifact
import com.example.morax.model.ArtifactResp
import com.example.morax.model.LocationResp
import com.example.morax.model.SearchResp
import com.example.morax.repo.ArtifactRepoImpl
import com.example.morax.repo.LocationsRepoImpl
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    val artifactRepo: ArtifactRepoImpl,
    val locationsRepo: LocationsRepoImpl
) : SearchService {
    override fun searchData(searchText: String): SearchResp {
        val artifactsResp = artifactRepo.listArtifact(searchText).map { artifact: Artifact -> ArtifactResp(artifact) }
        val locationsResp = locationsRepo.getLocations(searchText).map { location -> LocationResp(location) }
        return SearchResp(locationsResp, artifactsResp)
    }
}