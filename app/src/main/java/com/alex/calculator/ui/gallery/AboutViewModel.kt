package com.alex.calculator.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This calculator allows you to calculate the total length given two lengths in composite units."
    }
    val text: LiveData<String> = _text
}