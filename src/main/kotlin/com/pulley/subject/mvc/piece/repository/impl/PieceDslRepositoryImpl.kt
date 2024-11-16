package com.pulley.subject.mvc.piece.repository.impl

import com.pulley.subject.domain.Yn
import com.pulley.subject.domain.entity.QTbPiece.tbPiece
import com.pulley.subject.domain.entity.QTbPieceProblem.tbPieceProblem
import com.pulley.subject.domain.entity.QTbSolvedProblem.tbSolvedProblem
import com.pulley.subject.mvc.piece.repository.PieceDslRepository
import com.querydsl.core.annotations.QueryProjection
import com.querydsl.jpa.impl.JPAQueryFactory

class PieceDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : PieceDslRepository {
    override fun findAnalyzeReports(pieceId: Long): List<PieceAnalyzeReportPo> {
        return jpaQueryFactory
            .select(
                QPieceAnalyzeReportPo(
                    tbPiece.pieceId,
                    tbPiece.pieceName,
                    tbSolvedProblem.studentId,
                    tbSolvedProblem.problemId,
                    tbSolvedProblem.correctYn
                )
            )
            .from(tbPiece)
            .leftJoin(tbPieceProblem).on(tbPieceProblem.pieceProblemId.pieceId.eq(tbPiece.pieceId))
            .leftJoin(tbSolvedProblem).on(tbSolvedProblem.problemId.eq(tbPieceProblem.pieceProblemId.problemId))
            .where(tbPiece.pieceId.eq(pieceId))
            .fetch()
    }
}

data class PieceAnalyzeReportPo @QueryProjection constructor(
    val pieceId: Long,
    val pieceName: String,
    val studentId: Long?,
    val problemId: Long?,
    val correctYn: Yn?
)