package com.pulley.subject.mvc.problem.dto

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode

data class ProblemDto(
    val problemId: Long,
    val answer: Int,
    val unitCode: UnitCode,
    val problemType: ProblemType,
    val level: Int,
)