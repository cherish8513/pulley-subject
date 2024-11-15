package com.pulley.subject.mvc.piece.controller

import com.pulley.subject.mvc.common.ResponseDto
import com.pulley.subject.mvc.piece.dto.AddPieceDto
import com.pulley.subject.mvc.piece.service.PieceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pieces")
class PieceController(
    private val pieceService: PieceService
) {
    @PostMapping
    fun addPiece(@RequestBody @Valid addPieceDto: AddPieceDto): ResponseDto<Unit> {
        return ResponseDto(pieceService.addPiece(addPieceDto))
    }
}