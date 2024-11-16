package com.pulley.subject.mvc.piece.repository

import com.pulley.subject.domain.entity.TbPiece
import com.pulley.subject.mvc.piece.repository.impl.PieceAnalyzeReportPo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PieceRepository : JpaRepository<TbPiece, Long>, PieceDslRepository {
    fun findByUserIdAndPieceId(userId: Long, pieceId: Long): TbPiece?
}

interface PieceDslRepository {
    fun findAnalyzeReports(pieceId: Long): List<PieceAnalyzeReportPo>
}