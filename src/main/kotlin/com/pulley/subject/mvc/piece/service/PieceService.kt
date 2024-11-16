package com.pulley.subject.mvc.piece.service

import com.pulley.subject.domain.Yn
import com.pulley.subject.domain.entity.*
import com.pulley.subject.mvc.common.assertNotNull
import com.pulley.subject.mvc.piece.dto.AddPieceDto
import com.pulley.subject.mvc.piece.dto.SubmittedAnswer
import com.pulley.subject.mvc.piece.repository.PieceProblemRepository
import com.pulley.subject.mvc.piece.repository.PieceRepository
import com.pulley.subject.mvc.piece.repository.PieceStudentRepository
import com.pulley.subject.mvc.problem.dto.ProblemDto
import com.pulley.subject.mvc.problem.repository.ProblemRepository
import com.pulley.subject.mvc.problem.repository.SolvedProblemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class PieceService(
    private val pieceRepository: PieceRepository,
    private val pieceProblemRepository: PieceProblemRepository,
    private val problemRepository: ProblemRepository,
    private val pieceStudentRepository: PieceStudentRepository,
    private val solvedProblemRepository: SolvedProblemRepository
) {
    fun addPiece(addPieceDto: AddPieceDto) {
        if (problemRepository.findAllById(addPieceDto.problemIdList).size != addPieceDto.problemIdList.size) {
            throw RuntimeException("invalid problem id")
        }

        val savedPiece = pieceRepository.save(
            TbPiece(
                userId = addPieceDto.userId,
                pieceName = addPieceDto.pieceName
            )
        )

        pieceProblemRepository.saveAll(addPieceDto.problemIdList
            .map {
                TbPieceProblem(PieceProblemId(pieceId = savedPiece.pieceId.assertNotNull(), problemId = it))
            }
        )
    }

    fun addPieceToStudent(pieceId: Long, userId: Long, studentIds: List<Long>) {
        val piece = pieceRepository.findByUserIdAndPieceId(userId = userId, pieceId = pieceId).assertNotNull()
        pieceStudentRepository.saveAllIgnore(studentIds.map {
            TbPieceStudent(
                pieceStudentId = PieceStudentId(
                    pieceId = piece.pieceId.assertNotNull(),
                    studentId = it
                )
            )
        })
    }

    fun getPieceProblems(pieceId: Long): List<ProblemDto> {
        return problemRepository.findByPieceId(pieceId).map {
            ProblemDto(
                problemId = it.problemId.assertNotNull(),
                answer = it.answer,
                unitCode = it.unitCode,
                problemType = it.problemType,
                level = it.level
            )
        }
    }

    fun checkCorrect(pieceId: Long, studentId: Long, submittedAnswerList: List<SubmittedAnswer>) {
        val problems = problemRepository.findByPieceId(pieceId).associateBy { it.problemId }

        val submittedAnswerMap = submittedAnswerList.associateBy { it.problemId }

        val solvedProblems = solvedProblemRepository.findByStudentIdAndProblemIds(studentId = studentId, submittedAnswerList.map { it.problemId })

        solvedProblems.forEach { solvedProblem ->
            val newAnswer = submittedAnswerMap[solvedProblem.problemId].assertNotNull()
            val problem = problems[solvedProblem.problemId].assertNotNull()
            solvedProblem.submittedAnswer = newAnswer.answer
            solvedProblem.correctYn = if (solvedProblem.submittedAnswer == problem.answer) Yn.Y else Yn.N
        }

        solvedProblemRepository.saveAll(submittedAnswerList
            .filterNot { submittedAnswer -> solvedProblems.map { it.problemId }.contains(submittedAnswer.problemId) }
            .map {
                val problem = problems[it.problemId].assertNotNull()
                TbSolvedProblem(
                    studentId = studentId,
                    problemId = it.problemId,
                    submittedAnswer = it.answer,
                    correctYn = if (it.answer == problem.answer) Yn.Y else Yn.N
                )
            }
        )
    }
}