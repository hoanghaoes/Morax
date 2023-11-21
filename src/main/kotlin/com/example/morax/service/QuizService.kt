package com.example.morax.service

import com.example.morax.model.*
import reactor.core.publisher.Mono

interface QuizService {
    fun addQuiz(quizReq: QuizReq): Mono<QuizResp>
    fun updateQuiz(quizReq: QuizReq): Mono<QuizResp>
    fun getQuizzes(): Mono<List<QuizResp>>
    fun getQuizzesByLocationId(locationId: String): Mono<List<QuizResp>>
    fun addAnswer(answers: List<AnswerReq>): Mono<List<AnswerResp>>
    fun updateAnswer(answers: List<AnswerReq>, quizId: String): Mono<List<AnswerResp>>
    fun getQuizAnswer(quizId: String): List<AnswerResp>
}