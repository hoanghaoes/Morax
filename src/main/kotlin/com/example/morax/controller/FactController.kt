package com.example.morax.controller

import com.example.morax.model.Fact
import com.example.morax.model.FactReq
import com.example.morax.model.FactResp
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/facts")
class FactController {

    @PostMapping
    fun createFact(@RequestBody fact: FactReq): Mono<FactResp> {
        return TODO("Provide the return value")
    }

    @GetMapping
    fun getAllFacts(): Mono<FactResp> {
        return TODO("Provide the return value")
    }

    @GetMapping("/{id}")
    fun getFactById(@PathVariable id: String): Mono<FactResp> {
        return TODO("Provide the return value")
    }

    @PutMapping("/{id}")
    fun updateFact(@PathVariable id: String, @RequestBody fact: FactReq): Mono<FactResp> {
        return TODO("Provide the return value")
    }

    @DeleteMapping("/{id}")
    fun deleteFact(@PathVariable id: String): Mono<FactResp> {
        return TODO("Provide the return value")
    }
}