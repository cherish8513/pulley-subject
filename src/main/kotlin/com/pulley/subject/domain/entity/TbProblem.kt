package com.pulley.subject.domain.entity

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode
import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@QueryEntity
@Entity
class TbProblem(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val problemId: Long? = null,
    @Enumerated(EnumType.STRING)
    val unitCode: UnitCode,
    val level: Int,
    @Enumerated(EnumType.STRING)
    val problemType: ProblemType,
    val answer: Int
)