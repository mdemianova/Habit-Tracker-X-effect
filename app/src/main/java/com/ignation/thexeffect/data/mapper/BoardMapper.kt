package com.ignation.thexeffect.data.mapper

import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.domain.models.Board

fun Board.toBoardEntity(): BoardEntity {
    return BoardEntity(
        id = this.id,
        title = this.title,
        isActive = this.isActive,
        startDate = this.startDate
    )
}

fun BoardEntity.toBoard(): Board {
    return Board(
        id = this.id,
        title = this.title,
        isActive = this.isActive,
        startDate = this.startDate
    )
}