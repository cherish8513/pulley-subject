package com.pulley.subject.mvc.problem.dto

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode
import com.pulley.subject.mvc.common.Level

data class GetProblemDto(
    val totalCount: Long,
    val unitCodeList: List<UnitCode>,
    val problemType: ProblemType,
    val level: Level,
)