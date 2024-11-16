package com.pulley.subject.mvc.piece.service

import com.pulley.subject.mvc.piece.dto.AddPieceDto
import com.pulley.subject.mvc.piece.dto.SubmittedAnswer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PieceServiceTest {

    @Autowired
    lateinit var pieceService: PieceService

    @Test
    fun successAddPiece() {
        pieceService.addPiece(AddPieceDto(
            userId = 1L,
            pieceName = "test",
            problemIdList = listOf(1001L, 1002L)
        ))
    }

    @Test
    fun successAddPieceToStudent() {
        pieceService.addPieceToStudent(
            pieceId = 1L,
            userId = 1L,
            studentIds = listOf(1L, 2L)
        )
    }

    @Test
    fun successAddDuplicatePieceToStudent() {
        pieceService.addPieceToStudent(
            pieceId = 1L,
            userId = 1L,
            studentIds = listOf(1L, 2L)
        )

        pieceService.addPieceToStudent(
            pieceId = 1L,
            userId = 1L,
            studentIds = listOf(1L, 2L)
        )
    }

    @Test
    fun successGetPieceProblems() {
        val testProblems = pieceService.getPieceProblems(pieceId = 1L)

        testProblems.forEach {
            println("problemId: ${it.problemId}")
        }
    }

    @Test
    fun successCheckCorrect() {
        pieceService.checkCorrect(pieceId = 1L, studentId = 1L, submittedAnswerList = listOf(
            SubmittedAnswer(
                problemId = 1516L,
                answer = 1
            ),
            SubmittedAnswer(
                problemId = 1517L,
                answer = 1
            ),
        ))

        pieceService.checkCorrect(pieceId = 1L, studentId = 1L, submittedAnswerList = listOf(
            SubmittedAnswer(
                problemId = 1516L,
                answer = 2
            ),
            SubmittedAnswer(
                problemId = 1517L,
                answer = 3
            ),
        ))
    }
}