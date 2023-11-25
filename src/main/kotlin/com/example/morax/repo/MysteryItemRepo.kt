package com.example.morax.repo

import com.example.morax.model.MysteryItem
import com.example.morax.model.UserHistoryMysteryItem

interface MysteryItemRepo {
    fun addItem(item: MysteryItem): MysteryItem
    fun update(item: MysteryItem): MysteryItem
    fun getItems(locationId: String?): List<MysteryItem>
    fun getItemById(itemId: String): MysteryItem
    fun addFoundItem(history: UserHistoryMysteryItem): UserHistoryMysteryItem
    fun getFoundItems(locationId: String?): List<UserHistoryMysteryItem>
}