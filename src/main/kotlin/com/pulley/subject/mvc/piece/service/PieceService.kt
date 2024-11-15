package com.pulley.subject.mvc.piece.service

import com.pulley.subject.domain.entity.TbPiece
import com.pulley.subject.domain.entity.TbPieceProblem
import com.pulley.subject.domain.entity.TbPieceProblemId
import com.pulley.subject.mvc.common.assertNotNull
import com.pulley.subject.mvc.piece.dto.AddPieceDto
import com.pulley.subject.mvc.piece.repository.PieceProblemRepository
import com.pulley.subject.mvc.piece.repository.PieceRepository
import com.pulley.subject.mvc.problem.repository.ProblemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class PieceService(
    private val pieceRepository: PieceRepository,
    private val pieceProblemRepository: PieceProblemRepository,
    private val problemRepository: ProblemRepository
) {
    fun addPiece(addPieceDto: AddPieceDto) {
        if (problemRepository.findAllById(addPieceDto.problemIdList).size != addPieceDto.problemIdList.size) {
            throw RuntimeException("invalid problem id")
        }

        val savedPiece = pieceRepository.save(
            TbPiece(
                userId = addPieceDto.userId,
                pieceName = addPieceDto.pieceName
            )
        )

        pieceProblemRepository.saveAll(addPieceDto.problemIdList
            .map {
                TbPieceProblem(TbPieceProblemId(pieceId = savedPiece.pieceId.assertNotNull(), problemId = it))
            }
        )
    }
}