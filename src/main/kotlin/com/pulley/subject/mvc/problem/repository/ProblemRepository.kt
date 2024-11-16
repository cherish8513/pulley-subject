package com.pulley.subject.mvc.problem.repository

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode
import com.pulley.subject.domain.entity.TbProblem
import com.pulley.subject.mvc.common.Level
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProblemRepository : JpaRepository<TbProblem, Long>, ProblemDslRepository {
    @Query("""
        select tp 
        from TbProblem tp
        join TbPieceProblem tpp on tpp.pieceProblemId.problemId = tp.problemId
        where tpp.pieceProblemId.pieceId = :pieceId
    """)
    fun findByPieceId(pieceId: Long): List<TbProblem>
}

interface ProblemDslRepository {
    fun findByConditions(unitCodeList: List<UnitCode>, problemType: ProblemType, level: Level, count: Long): List<TbProblem>
}