package com.pulley.subject.mvc.piece.repository

import com.pulley.subject.domain.entity.TbPieceProblem
import org.springframework.data.jpa.repository.JpaRepository

interface PieceProblemRepository: JpaRepository<TbPieceProblem, Long>