package com.example.morax.repo

import com.example.morax.model.Fact

interface FactRepo {
    fun addFact(fact: Fact): Fact
    fun getAllFacts(): List<Fact>
    fun getFactById(id: String): Fact
    fun updateFact(updatedFact: Fact): Fact
    fun deleteFact(id: String)
}