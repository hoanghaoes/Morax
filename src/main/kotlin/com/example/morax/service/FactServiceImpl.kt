package com.example.morax.service

import com.example.morax.model.Fact
import com.example.morax.model.FactReq
import com.example.morax.model.FactResp
import com.example.morax.repo.FactRepoImpl

class FactServiceImpl(
    private val factRepo: FactRepoImpl
): FactService {
    override fun createFact(fact: FactReq): FactResp {
        val newFact = Fact(fact)
        return FactResp(factRepo.addFact(newFact))
    }

    override fun getAllFacts(): List<FactResp> {
        return factRepo.getAllFacts().map { FactResp(it) }
    }

    override fun getFactById(id: String): FactResp {
        return FactResp(factRepo.getFactById(id))
    }

    override fun updateFact(id: String, updatedFact: FactReq): FactResp {
        TODO("Not yet implemented")
    }

    override fun deleteFact(id: String): FactResp {
        TODO("Not yet implemented")
    }
}