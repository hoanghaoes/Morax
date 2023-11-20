package com.example.morax.controller

import com.example.morax.model.QuizReq
import com.example.morax.model.QuizResp
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1/quizzes")
@Tag(name = "Quiz")
class QuizController {

    @PostMapping("", MediaType.MULTIPART_FORM_DATA_VALUE)
    fun addQuiz(@ModelAttribute quizReq: QuizReq): Mono<QuizResp> {

        return TODO("Provide the return value")
    }
}