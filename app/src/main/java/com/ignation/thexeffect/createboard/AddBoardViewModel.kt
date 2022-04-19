package com.ignation.thexeffect.createboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddBoardViewModel @Inject constructor() : ViewModel() {

    private val _boardTitle = MutableLiveData<String>()
    val boardTitle: LiveData<String> = _boardTitle

    fun setTitle(title: String) {
        _boardTitle.value = title
    }
}