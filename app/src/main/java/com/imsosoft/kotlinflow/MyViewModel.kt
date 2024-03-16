package com.imsosoft.kotlinflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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



    // LiveData vs StateFlow vs SharedFlow

    // LiveData
    private val _liveData = MutableLiveData("LiveData")
    val liveData: LiveData<String> = _liveData

    fun updateLiveData() {
        _liveData.value = "LiveData Updated"
    }


    // StateFlow
    private val _stateFlow = MutableStateFlow("StateFlow")
    val stateFlow = _stateFlow.asStateFlow()

    fun updateStateFlow() {
        _stateFlow.value = "StateFlow Updated"
    }


    // SharedFlow
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun updateSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow Updated")

        }
    }


    // make stateful flow
    val statefulCounter = flow {
        var count = 10
        while (true) {
            delay(1000L)
            println("flow working")
            emit(count++)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)


}