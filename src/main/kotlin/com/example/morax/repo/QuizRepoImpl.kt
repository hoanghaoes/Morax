package com.example.morax.repo

import com.example.morax.model.Answer
import com.example.morax.model.Quiz
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class QuizRepoImpl(
    val mongoTemplate: MongoTemplate,
    @Value("\${data.mongodb.table.quizzes}") val quizCol: String,
    @Value("\${data.mongodb.table.answers}") val answerCol: String
) : QuizRepo {
    override fun addQuiz(quiz: Quiz): Quiz {
        return mongoTemplate.save(quiz, quizCol)
    }

    override fun updateQuiz(quiz: Quiz): Quiz {
        return mongoTemplate.save(quiz, quizCol)
    }

    override fun getQuizzes(): List<Quiz> {
        return mongoTemplate.findAll<Quiz>(quizCol)
    }

    override fun getQuizzesByLocationId(locationId: String): List<Quiz> {
        val query = Query()
        query.addCriteria(Criteria.where("locationId").isEqualTo(locationId))
        return mongoTemplate.find(query, Quiz::class.java, quizCol)
    }

    override fun deleteQuiz(id: String): Quiz {
        val query = Query()
        query.addCriteria(Criteria.where("id").isEqualTo(id))
        return mongoTemplate.findAndRemove(query, Quiz::class.java, quizCol)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cannot find any quiz with id $id to delete"
            )
    }

    override fun addAnswers(answers: List<Answer>): List<Answer> {
        for(answer in answers) {
            mongoTemplate.save(answer, answerCol)
        }
        return answers
    }

    override fun updateAnswer(answers: List<Answer>, quizId: String): List<Answer> {
        val query = Query()
        query.addCriteria(Criteria.where("quizId").isEqualTo(quizId))
        mongoTemplate.findAndRemove(query, Answer::class.java, answerCol)

        for(answer in answers) {
            mongoTemplate.save(answer, answerCol)
        }
        return answers
    }

    override fun getQuizAnswer(quizId: String): List<Answer> {
        val query = Query()
        query.addCriteria(Criteria.where("quizId").isEqualTo(quizId))
        return mongoTemplate.find(query, Answer::class.java, answerCol)
    }
}