package com.pulley.subject.mvc.problem.service

import com.pulley.subject.domain.ProblemType
import com.pulley.subject.domain.UnitCode
import com.pulley.subject.mvc.common.Level
import com.pulley.subject.mvc.problem.dto.GetProblemDto
import com.pulley.subject.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProblemServiceTest {
    @Autowired
    lateinit var problemService: ProblemService

    @Test
    fun successGetProblems() {
        problemService.getProblems(GetProblemDto(
            totalCount = 1,
            unitCodeList = listOf(UnitCode.uc1503),
            problemType = ProblemType.SELECTION,
            level = Level.LOW
        ))
    }

    @Test
    fun getLowProblems() {
        // given
        val providedTotalCount = 17L
        val providedUnitCodeList = listOf(UnitCode.uc1584, UnitCode.uc1583, UnitCode.uc1582, UnitCode.uc1581, UnitCode.uc1580)
        val providedProblemType = ProblemType.SELECTION
        val providedLevel = Level.LOW

        // when
        val resultList = problemService.getProblems(GetProblemDto(totalCount = providedTotalCount, unitCodeList = providedUnitCodeList, problemType = providedProblemType, level = providedLevel))
        val resultLevelGroups = resultList.groupBy { when(it.level) {
            1 -> "LOW"
            in 2..4 -> "MIDDLE"
            5 -> "HIGH"
            else -> throw RuntimeException()
        } }

        // then
        (resultList.size <= providedTotalCount) shouldBe true
        resultList.forEach { providedUnitCodeList.contains(it.unitCode) shouldBe true }
        println(resultList.size)
        println("LOW Level : ${resultLevelGroups["LOW"]?.size ?: 0}")
        println("MIDDLE Level : ${resultLevelGroups["MIDDLE"]?.size ?: 0}")
        println("HIGH Level : ${resultLevelGroups["HIGH"]?.size ?: 0}")
        resultList.forEach {
            println("Level : ${it.level}    problemId : ${it.problemId}")
        }
    }

    @Test
    fun successGenerateProblemCount() {
        // given & when
        val lowLevelProblems = ProblemCount(Level.LOW, 17)
        val midLevelProblems = ProblemCount(Level.MIDDLE, 23)
        val highLevelProblems = ProblemCount(Level.HIGH, 31)

        // then
        lowLevelProblems.lowProblemCount shouldBe 9L
        lowLevelProblems.midProblemCount shouldBe 5L
        lowLevelProblems.highProblemCount shouldBe 3L

        midLevelProblems.lowProblemCount shouldBe 5L
        midLevelProblems.midProblemCount shouldBe 13L
        midLevelProblems.highProblemCount shouldBe 5L

        highLevelProblems.lowProblemCount shouldBe 6L
        highLevelProblems.midProblemCount shouldBe 9L
        highLevelProblems.highProblemCount shouldBe 16L
    }
}