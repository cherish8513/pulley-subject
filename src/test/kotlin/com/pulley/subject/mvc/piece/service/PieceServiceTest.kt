package com.pulley.subject.mvc.piece.service

import com.pulley.subject.mvc.piece.dto.AddPieceDto
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
}