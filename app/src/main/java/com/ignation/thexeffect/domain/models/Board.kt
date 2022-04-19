package com.ignation.thexeffect.domain.models

import com.ignation.thexeffect.data.local.entities.BoardEntity

data class Board(
    var id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long
) {
    fun asDatabaseModel(): BoardEntity {
        return BoardEntity(
            title = this.title,
            isActive = this.isActive,
            startDate = this.startDate
        )
    }
}


