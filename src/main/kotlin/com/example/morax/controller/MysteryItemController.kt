package com.example.morax.controller

import com.example.morax.model.MysteryItemReq
import com.example.morax.model.MysteryItemResp
import com.example.morax.service.MysteryItemServiceImpl
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/mystery_item")
class MysteryItemController(
    private val mysteryItemService: MysteryItemServiceImpl
){
    @Operation(
        summary = "Add new mystery item",
        description = "Add new mystery item",
    )
    @PostMapping("", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun addItem(@ModelAttribute mysteryItemReq: MysteryItemReq): Mono<MysteryItemResp> {
        return Mono.just(mysteryItemService.addItem(mysteryItemReq))
    }

    @Operation(
        summary = "Update mystery item",
        description = "Update mystery item with provided id",
    )
    @PutMapping("/{itemId}", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun updateItem(
        @ModelAttribute mysteryItemReq: MysteryItemReq,
        @PathVariable itemId: String
    ): Mono<MysteryItemResp> {
        return Mono.just(mysteryItemService.updateItem(mysteryItemReq, itemId))
    }

    @Operation(
        summary = "Get mystery item list",
        description = "Get whole mystery item list or mystery item list in location",
    )
    @GetMapping
    fun getItems(@RequestParam(required = false) locationId: String?): Mono<List<MysteryItemResp>> {
        return Mono.just(mysteryItemService.getItems(locationId))
    }

    @Operation(
        summary = "Call when find an item",
        description = "Add new mystery item history for user",
    )
    @PostMapping("/{itemId}/found")
    fun foundItem(@PathVariable("itemId") itemId: String): Mono<MysteryItemResp> {
        return Mono.just(mysteryItemService.foundItem(itemId))
    }
}