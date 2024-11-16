package com.pulley.subject.mvc.piece.dto

data class PieceAnalyzeDto(
    val pieceId: Long,
    val pieceName: String,
    val studentReport: List<StudentReport>,
    val problemReport: List<ProblemReport>

)

data class StudentReport(
    val studentId: Long,
    val pieceCorrectRate: Double,
)

data class ProblemReport(
    val problemId: Long,
    val problemCorrectRate: Double
)