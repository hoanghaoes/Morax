package com.example.morax.service

import com.example.morax.model.MysteryItemReq
import com.example.morax.model.MysteryItemResp

interface MysteryItemService {
    fun addItem(mysteryItemReq: MysteryItemReq): MysteryItemResp
    fun updateItem(mysteryItemReq: MysteryItemReq, itemId: String): MysteryItemResp
    fun getItems(locationId: String?): List<MysteryItemResp>
    fun getItemById(itemId: String, isFound: Boolean = false, foundAt: String? = null): MysteryItemResp
    fun foundItem(itemId: String): MysteryItemResp
}