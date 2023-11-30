package com.example.morax.repo

import com.example.morax.model.Answer
import com.example.morax.model.Quiz

interface QuizRepo {
    fun addQuiz(quiz: Quiz): Quiz
    fun updateQuiz(quiz: Quiz): Quiz
    fun getQuizzes(): List<Quiz>
    fun quizById(quizId: String): Quiz
    fun getQuizzesByLocationId(locationId: String): List<Quiz>
    fun deleteQuiz(id: String): Quiz
    fun addAnswers(answers: List<Answer>): List<Answer>
    fun updateAnswer(answers: List<Answer>, quizId: String): List<Answer>
    fun getQuizAnswer(quizId: String): List<Answer>
    fun answerById(answerId: String): Answer
}