package com.example.morax.repo

import com.example.morax.model.MysteryItem
import com.example.morax.model.User
import com.example.morax.model.UserHistoryMysteryItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class MysteryItemRepoImpl(
    val mongoTemplate: MongoTemplate,
    @Value("\${data.mongodb.table.mystery-items}") val itemCol: String,
    @Value("\${data.mongodb.table.user-history-items}") val historyItemCol: String
) : MysteryItemRepo {
    override fun addItem(item: MysteryItem): MysteryItem {
        return mongoTemplate.save(item, itemCol)
    }

    override fun update(item: MysteryItem): MysteryItem {
        return mongoTemplate.save(item, itemCol)
    }

    override fun getItems(locationId: String?): List<MysteryItem> {
        val query = Query()
        if (locationId != null && locationId != "") {
            query.addCriteria(Criteria.where("locationId").isEqualTo(locationId))
        }
        return mongoTemplate.find(query, MysteryItem::class.java, itemCol)
    }

    override fun getItemById(itemId: String): MysteryItem {
        return mongoTemplate.findById(itemId, MysteryItem::class.java, itemCol) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Can't find any mystery item with id $itemId"
        )
    }

    override fun addFoundItem(history: UserHistoryMysteryItem): UserHistoryMysteryItem {
        return mongoTemplate.save(history, historyItemCol)
    }

    override fun getFoundItems(locationId: String?): List<UserHistoryMysteryItem> {
        val userId = User.currentUser.id
        val query = Query()
        query.addCriteria(Criteria.where("userId").isEqualTo(userId))
        if (locationId != null && locationId != "") {
            query.addCriteria(Criteria.where("locationId").isEqualTo(locationId))
        }
        return mongoTemplate.find(query, UserHistoryMysteryItem::class.java, historyItemCol)
    }
}