package com.pulley.subject.domain.entity

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import java.io.Serializable

@QueryEntity
@Entity
class TbPieceStudent (
    @EmbeddedId
    val tbPieceStudentId: TbPieceStudentId
)

@Embeddable
data class TbPieceStudentId(
    val pieceId: Long,
    val studentId: Long,
) : Serializable