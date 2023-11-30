package com.example.morax.repo

import com.example.morax.model.Point

interface PointRepo {
    fun addPoint(point: Point): Point
    fun pointsByUserId(userId: String): List<Point>
}