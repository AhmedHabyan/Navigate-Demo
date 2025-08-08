package com.example.demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ActivityViewModel:ViewModel() {


    private val _evenSharedFlow = MutableSharedFlow<Event>()
    val eventSharedFlow = _evenSharedFlow


    fun sendEvent(event:Event){

        viewModelScope.launch {
                 //emit using shared flow
            Log.e("event","in view model")
                _evenSharedFlow.emit(event)
            }


    }


}