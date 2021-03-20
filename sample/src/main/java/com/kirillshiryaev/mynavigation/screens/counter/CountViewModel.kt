package com.kirillshiryaev.mynavigation.screens.counter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CountViewModel(application: Application) : AndroidViewModel(application) {

    private var counter = 0

    private val _count = MutableLiveData<Int>()

    val count: LiveData<Int> = _count

    fun add() {
        _count.value = ++counter
    }

}