package com.pulley.subject.domain.entity

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import java.io.Serializable

@QueryEntity
@Entity
class TbPieceProblem (
    @EmbeddedId
    val tbPieceProblemId: TbPieceProblemId
)

@Embeddable
data class TbPieceProblemId(
    val pieceId: Long,
    val problemId: Long,
) : Serializable