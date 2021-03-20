package com.kirillshiryaev.mynavigation.screens.stringer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StringerViewModel(application: Application) : AndroidViewModel(application) {

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> = _text

    fun addText(text: String) {
        _text.value = text
    }

}