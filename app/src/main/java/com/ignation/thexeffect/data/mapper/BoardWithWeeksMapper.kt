package com.ignation.thexeffect.data.mapper

import com.ignation.thexeffect.data.local.entities.relations.BoardWithWeeksData
import com.ignation.thexeffect.domain.models.BoardWithWeeks

fun BoardWithWeeksData.toBoardWithWeeks(): BoardWithWeeks {
    return BoardWithWeeks(
        board = this.board.toBoard(),
        weeks = this.weeks.toWeekList()
    )
}
