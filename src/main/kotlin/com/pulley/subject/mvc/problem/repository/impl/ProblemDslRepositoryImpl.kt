package com.pulley.subject.mvc.problem.repository.impl

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode
import com.pulley.subject.domain.entity.QTbProblem
import com.pulley.subject.domain.entity.QTbProblem.tbProblem
import com.pulley.subject.domain.entity.TbProblem
import com.pulley.subject.mvc.common.Level
import com.pulley.subject.mvc.problem.repository.ProblemDslRepository
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory


class ProblemDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): ProblemDslRepository {
    override fun findByConditions(unitCodeList: List<UnitCode>, problemType: ProblemType, level: Level, count: Long): List<TbProblem> {
        return jpaQueryFactory
            .select(tbProblem)
            .from(tbProblem)
            .where(
                tbProblem.unitCode.`in`(unitCodeList),
                tbProblem.problemType.eq(problemType),
                tbProblem.eqLevel(level)
            )
            .limit(count)
            .orderBy(Expressions.numberTemplate(Double::class.java, "function('rand')").asc())
            .fetch()
    }
}

fun QTbProblem.eqLevel(level: Level): BooleanExpression = when (level) {
    Level.LOW -> this.level.eq(1)
    Level.MIDDLE -> this.level.between(2, 4)
    Level.HIGH -> this.level.eq(5)
}