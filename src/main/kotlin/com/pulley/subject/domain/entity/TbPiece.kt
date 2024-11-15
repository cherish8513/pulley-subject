package com.pulley.subject.domain.entity

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@QueryEntity
@Entity
class TbPiece (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val pieceId: Long? = null,
    val userId: Long,
    val pieceName: String
)