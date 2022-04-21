package com.ignation.thexeffect.title

import androidx.lifecycle.ViewModel
import com.ignation.thexeffect.data.repository.HabitRepositoryImpl
import javax.inject.Inject

class TitleViewModel @Inject constructor(
    private val habitRepositoryImpl: HabitRepositoryImpl
) : ViewModel() {


}