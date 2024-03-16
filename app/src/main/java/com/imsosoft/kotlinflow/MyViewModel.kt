package com.imsosoft.kotlinflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MyViewModel: ViewModel() {

    val countDownTimer = flow<Int> {

        val countFrom = 10
        var counter = countFrom

        emit(countFrom)

        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter)
        }

    }

}