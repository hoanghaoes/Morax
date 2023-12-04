package com.example.morax.service

import com.example.morax.model.FactReq
import com.example.morax.model.FactResp

interface FactService {
    fun createFact(fact: FactReq): FactResp
    fun getAllFacts(): List<FactResp>
    fun getFactById(id: String): FactResp
    fun updateFact(id: String, updatedFact: FactReq): FactResp
    fun deleteFact(id: String): FactResp
}