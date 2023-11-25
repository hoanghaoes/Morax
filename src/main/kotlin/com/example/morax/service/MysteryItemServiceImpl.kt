package com.example.morax.service

import com.example.morax.model.*
import com.example.morax.repo.MysteryItemRepoImpl
import org.springframework.stereotype.Service

@Service
class MysteryItemServiceImpl(
    private val mysteryItemRepo: MysteryItemRepoImpl
): MysteryItemService {
    override fun addItem(mysteryItemReq: MysteryItemReq): MysteryItemResp {
        val mysteryItem = mysteryItemRepo.addItem(MysteryItem(mysteryItemReq))
        return MysteryItemResp(mysteryItem)
    }

    override fun updateItem(mysteryItemReq: MysteryItemReq, itemId: String): MysteryItemResp {
        val oldItem = mysteryItemRepo.getItemById(itemId)
        val updateItem = oldItem.update(mysteryItemReq)
        return MysteryItemResp(mysteryItemRepo.update(updateItem))
    }

    override fun getItems(locationId: String?): List<MysteryItemResp> {
        val items = mysteryItemRepo.getItems(locationId)
        val foundItems = mysteryItemRepo.getFoundItems(locationId)
        return items.map { mysteryItem ->
            val itemIds = foundItems.filter { history -> history.itemId == mysteryItem.id }
            val isFound = itemIds.isNotEmpty()
            if(isFound) return@map MysteryItemResp(mysteryItem, true, itemIds[0].foundAt.toString())
            else return@map MysteryItemResp(mysteryItem)
        }
    }

    override fun getItemById(itemId: String, isFound: Boolean, foundAt: String?): MysteryItemResp {
        val mysteryItem = mysteryItemRepo.getItemById(itemId)
        return MysteryItemResp(mysteryItem, isFound, foundAt)
    }

    override fun foundItem(itemId: String): MysteryItemResp {
        val userId = User.currentUser.id
        val historyMysteryItem = UserHistoryMysteryItem(userId, itemId)
        mysteryItemRepo.addFoundItem(historyMysteryItem)
        return getItemById(itemId, true, historyMysteryItem.foundAt.toString())
    }
}