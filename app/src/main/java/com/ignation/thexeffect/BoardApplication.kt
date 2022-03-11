package com.ignation.thexeffect

import android.app.Application
import com.ignation.thexeffect.database.BoardRoomDatabase

class BoardApplication : Application() {
    val database: BoardRoomDatabase by lazy { BoardRoomDatabase.getDatabase(this) }
}