package com.example.morax.config.database

import com.mongodb.*
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index
import org.springframework.transaction.support.TransactionTemplate
import java.util.concurrent.TimeUnit


@Configuration
class MongoConfig(
    @Value("\${data.mongodb.uri}") val mongoUri: String,
    @Value("\${data.mongodb.database:#{'morax'}}") val dbName: String
    ) {

    @Bean
    fun transactionManager(dbFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(dbFactory)
    }

    fun mongoClient(mongoUri: String): MongoClient {
        val connectionStr = ConnectionString(mongoUri)
        val mongoClientSettings = MongoClientSettings.builder()
            .retryWrites(true)
            .applyConnectionString(connectionStr)
            .applyToConnectionPoolSettings{builder ->
                builder.maxSize(100)
                    .minSize(5)
                    .maxConnectionLifeTime(30, TimeUnit.MINUTES)
            }
            .applyToSocketSettings{builder ->
                builder.connectTimeout(2000, TimeUnit.MILLISECONDS)
            }
            .writeConcern(WriteConcern.ACKNOWLEDGED)
            .readConcern(ReadConcern.AVAILABLE)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    fun mongoTemplate(dbName: String, mongoUri: String): MongoTemplate {
        val template = MongoTemplate(mongoClient(mongoUri), dbName)
        template.setReadPreference(ReadPreference.secondaryPreferred())
        template.setWriteConcern(WriteConcern.MAJORITY)
        return template
    }

    @Bean
    @Primary
    fun mongoTemplate(): MongoTemplate {
        return this.mongoTemplate(this.dbName, this.mongoUri)
    }

}