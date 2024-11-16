package com.pulley.subject.domain.entity

import com.pulley.subject.domain.Yn
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
class TbSolvedProblem (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val solvedProblemId: Long? = null,
    val studentId: Long,
    val problemId: Long,
    var submittedAnswer: Int,
    @Enumerated(EnumType.STRING)
    var correctYn: Yn
)