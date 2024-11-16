package com.pulley.subject.mvc.problem.repository

import com.pulley.subject.domain.entity.TbSolvedProblem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SolvedProblemRepository : JpaRepository<TbSolvedProblem, Long> {
    @Query("""
        select tsp
          from TbSolvedProblem tsp
         where tsp.studentId = :studentId
           and tsp.problemId in :problemIds
    """)
    fun findByStudentIdAndProblemIds(studentId: Long, problemIds: List<Long>): List<TbSolvedProblem>
}