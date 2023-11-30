package com.example.morax.repo

import com.example.morax.model.Point
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Component


@Component
class PointRepoImpl(
    private val mongoTemplate: MongoTemplate,
    @Value("\${data.mongodb.table.points}") val pointCol: String
) : PointRepo {
    override fun addPoint(point: Point): Point {
        return mongoTemplate.save(point, pointCol)
    }

    override fun pointsByUserId(userId: String): List<Point> {
        val query = Query()
        query.addCriteria(Criteria.where("userId").isEqualTo(userId))
        return mongoTemplate.find(query, Point::class.java, pointCol)
    }
}