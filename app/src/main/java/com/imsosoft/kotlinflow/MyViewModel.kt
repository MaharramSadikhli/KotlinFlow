package com.imsosoft.kotlinflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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


    init {
        collectFlow()
    }

    private fun collectFlow() {

        viewModelScope.launch {
            countDownTimer
                .filter {
                    it % 2 == 0
                }
                .map {
                    it * it
                }
                .collect {
                println("Counter is $it")
            }
        }

        // 1 and 2 are the same

        /* 1.

        countDownTimer.onEach {
            delay(1000)
            println(it)
        }.launchIn(viewModelScope)



         */

        /* 2.
        viewModelScope.launch {
            countDownTimer.collect {
                println(it)
            }
        }

         */


        /* 3.
        viewModelScope.launch {
            countDownTimer.collectLatest {
                delay(2000)
                println(it)
            }
        }


         */
    }

}