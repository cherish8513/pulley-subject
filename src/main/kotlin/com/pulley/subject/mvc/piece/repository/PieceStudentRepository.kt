package com.pulley.subject.mvc.piece.repository

import com.pulley.subject.domain.entity.TbPieceStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PieceStudentRepository: JpaRepository<TbPieceStudent, Long>, PieceStudentJdbcTemplateRepository

interface PieceStudentJdbcTemplateRepository {
    fun saveAllIgnore(tbPieceStudentList: List<TbPieceStudent>)
}