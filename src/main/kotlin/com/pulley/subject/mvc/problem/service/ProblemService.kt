package com.pulley.subject.mvc.problem.service

import com.pulley.subject.domain.entity.TbProblem
import com.pulley.subject.mvc.common.Level
import com.pulley.subject.mvc.common.assertNotNull
import com.pulley.subject.mvc.problem.repository.ProblemRepository
import com.pulley.subject.mvc.problem.dto.GetProblemDto
import com.pulley.subject.mvc.problem.dto.ProblemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class ProblemService(
    private val problemRepository: ProblemRepository
) {
    fun getProblems(getProblemDto: GetProblemDto): List<ProblemDto> {
        val problemCount = ProblemCount(getProblemDto.level, getProblemDto.totalCount)
        val problems = mutableListOf<TbProblem>()

        if(problemCount.lowProblemCount > 0) {
            problems.addAll(problemRepository.findByConditions(unitCodeList = getProblemDto.unitCodeList, problemType = getProblemDto.problemType, level = Level.LOW, count = problemCount.lowProblemCount))
        }
        if(problemCount.midProblemCount > 0) {
            problems.addAll(problemRepository.findByConditions(unitCodeList = getProblemDto.unitCodeList, problemType = getProblemDto.problemType, level = Level.MIDDLE, count = problemCount.midProblemCount))
        }
        if(problemCount.highProblemCount > 0) {
            problems.addAll(problemRepository.findByConditions(unitCodeList = getProblemDto.unitCodeList, problemType = getProblemDto.problemType, level = Level.HIGH, count = problemCount.highProblemCount))
        }

        return problems.map {
            ProblemDto(
                problemId = it.problemId.assertNotNull(),
                answer = it.answer,
                unitCode = it.unitCode,
                problemType = it.problemType,
                level = it.level
            )
        }
    }
}

data class ProblemCount(
    val level: Level,
    val totalCount: Long
) {
    val lowProblemCount: Long
    val midProblemCount: Long
    val highProblemCount: Long

    init {
        var lowTempCount: Long
        var midTempCount: Long
        var highTempCount: Long

        when(level) {
            Level.LOW -> {
                lowTempCount = totalCount * 5 / 10
                midTempCount = totalCount * 3 / 10
                highTempCount = totalCount * 2 / 10

                if(lowTempCount + midTempCount + highTempCount != totalCount) {
                    lowTempCount += (totalCount - lowTempCount - midTempCount - highTempCount)
                }
            }

            Level.MIDDLE -> {
                lowTempCount = totalCount * 25 / 100
                midTempCount = totalCount * 5 / 10
                highTempCount = totalCount * 25 / 100

                if(lowTempCount + midTempCount + highTempCount != totalCount) {
                    midTempCount += (totalCount - lowTempCount - midTempCount - highTempCount)
                }
            }

            Level.HIGH -> {
                lowTempCount = totalCount * 2 / 10
                midTempCount = totalCount * 3 / 10
                highTempCount = totalCount * 5 / 10

                if(lowTempCount + midTempCount + highTempCount != totalCount) {
                    highTempCount += (totalCount - lowTempCount - midTempCount - highTempCount)
                }
            }
        }

        lowProblemCount = lowTempCount
        midProblemCount = midTempCount
        highProblemCount = highTempCount
    }
}