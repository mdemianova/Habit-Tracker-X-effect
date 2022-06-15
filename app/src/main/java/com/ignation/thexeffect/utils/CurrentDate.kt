package com.ignation.thexeffect.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CurrentDate {
    companion object {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}