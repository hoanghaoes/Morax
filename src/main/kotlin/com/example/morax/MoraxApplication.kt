package com.example.morax

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext




@SpringBootApplication
class MoraxApplication

fun main(args: Array<String>) {
	runApplication<MoraxApplication>(*args)
}

