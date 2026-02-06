package com.mon.gametracker.temp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempVM @Inject constructor(
    private val greeting: String
) : ViewModel() {

    fun message(): String = greeting

}

