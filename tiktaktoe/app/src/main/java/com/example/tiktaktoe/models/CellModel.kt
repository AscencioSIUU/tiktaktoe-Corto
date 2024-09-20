package com.example.tiktaktoe.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CellModel(val initialState: Int = 0) {
    var state by mutableStateOf(initialState)

    fun changeState(newState: Int){
        state = newState
    }
}
