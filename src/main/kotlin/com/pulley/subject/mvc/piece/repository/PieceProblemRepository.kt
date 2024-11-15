package com.pulley.subject.mvc.piece.repository

import com.pulley.subject.domain.entity.TbPieceProblem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PieceProblemRepository: JpaRepository<TbPieceProblem, Long>