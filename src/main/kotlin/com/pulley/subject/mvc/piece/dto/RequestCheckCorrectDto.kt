package com.pulley.subject.mvc.piece.dto

data class RequestCheckCorrectDto(
    val studentId: Long,
    val submittedAnswerList: List<SubmittedAnswer>
)

data class SubmittedAnswer(
    val problemId: Long,
    val answer: Int
)