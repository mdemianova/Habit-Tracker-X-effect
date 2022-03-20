package com.ignation.thexeffect.models

import com.ignation.thexeffect.database.entities.BoardDatabase

data class Board(
    var id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long
) {
    fun asDatabaseModel(): BoardDatabase {
        return BoardDatabase(
            title = this.title,
            isActive = this.isActive,
            startDate = this.startDate
        )
    }
}


