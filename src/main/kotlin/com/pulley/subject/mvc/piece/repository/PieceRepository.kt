package com.pulley.subject.mvc.piece.repository

import com.pulley.subject.domain.entity.TbPiece
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PieceRepository: JpaRepository<TbPiece, Long> {
    fun findByUserIdAndPieceId(userId: Long, pieceId: Long): TbPiece?
}