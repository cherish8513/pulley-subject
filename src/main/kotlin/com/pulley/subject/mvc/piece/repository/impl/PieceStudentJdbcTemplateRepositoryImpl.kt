package com.pulley.subject.mvc.piece.repository.impl

import com.pulley.subject.domain.entity.TbPieceStudent
import com.pulley.subject.mvc.piece.repository.PieceStudentJdbcTemplateRepository
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.PreparedStatement

class PieceStudentJdbcTemplateRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
): PieceStudentJdbcTemplateRepository {
    override fun saveAllIgnore(tbPieceStudentList: List<TbPieceStudent>) {
        if (tbPieceStudentList.isEmpty()) return

        // H2는 insert ignore를 지원하지 않음
        val sql = """
        MERGE INTO tb_piece_student (piece_id, student_id) VALUES (?, ?);
        """

        try {
            jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
                override fun setValues(ps: PreparedStatement, i: Int) {
                    val entity = tbPieceStudentList[i]
                    ps.setLong(1, entity.pieceStudentId.pieceId)
                    ps.setLong(2, entity.pieceStudentId.studentId)
                }

                override fun getBatchSize() = tbPieceStudentList.size
            })
        } catch (e: DataAccessException) {
            throw RuntimeException("Failed to batch insert piece students", e)
        }
    }
}