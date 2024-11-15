package com.pulley.subject.mvc.piece.dto

import jakarta.validation.constraints.Size

data class AddPieceDto(
    val userId: Long,
    val pieceName: String,
    @field:Size(min = 1, max = 50)
    val problemIdList: List<Long>,
)