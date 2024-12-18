package com.pulley.subject.mvc.piece.controller

import com.pulley.subject.mvc.common.ResponseDto
import com.pulley.subject.mvc.piece.dto.AddPieceDto
import com.pulley.subject.mvc.piece.dto.PieceAnalyzeDto
import com.pulley.subject.mvc.piece.dto.RequestCheckCorrectDto
import com.pulley.subject.mvc.piece.service.PieceService
import com.pulley.subject.mvc.problem.dto.ProblemDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/piece")
class PieceController(
    private val pieceService: PieceService
) {
    @PostMapping
    fun addPiece(@RequestBody @Valid addPieceDto: AddPieceDto): ResponseDto<Unit> {
        return ResponseDto(pieceService.addPiece(addPieceDto))
    }

    @PostMapping("/{pieceId}")
    fun addPieceToStudent(@PathVariable pieceId: Long, @RequestParam studentIds: List<Long>, @RequestParam userId: Long): ResponseDto<Unit> {
        return ResponseDto(pieceService.addPieceToStudent(pieceId = pieceId, userId = userId, studentIds = studentIds))
    }

    @GetMapping("/problems")
    fun getPiece(@RequestParam pieceId: Long): ResponseDto<List<ProblemDto>> {
        return ResponseDto(pieceService.getPieceProblems(pieceId = pieceId))
    }

    @PutMapping("/problems")
    fun checkCorrect(@RequestParam pieceId: Long, @RequestBody requestCheckCorrectDto: RequestCheckCorrectDto): ResponseDto<Unit> {
        return ResponseDto(pieceService.checkCorrect(pieceId = pieceId, studentId = requestCheckCorrectDto.studentId, submittedAnswerList = requestCheckCorrectDto.submittedAnswerList))
    }

    @GetMapping("/analyze")
    fun getAnalyzeReport(@RequestParam pieceId: Long): ResponseDto<PieceAnalyzeDto> {
        return ResponseDto(pieceService.getAnalyzeReport(pieceId = pieceId))
    }
}