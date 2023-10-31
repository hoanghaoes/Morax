package com.example.morax

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoraxApplication

fun main(args: Array<String>) {
	runApplication<MoraxApplication>(*args)
}
