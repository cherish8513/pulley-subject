package com.pulley.subject.mvc.problem.controller

import com.pulley.subject.mvc.common.ResponseDto
import com.pulley.subject.mvc.problem.dto.GetProblemDto
import com.pulley.subject.mvc.problem.dto.ProblemDto
import com.pulley.subject.mvc.problem.service.ProblemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/problems")
class ProblemController(
    private val problemService: ProblemService
) {
    @GetMapping
    fun getProblems(getProblemDto: GetProblemDto): ResponseDto<List<ProblemDto>> {
        return ResponseDto(problemService.getProblems(getProblemDto))
    }
}