package com.episi.androidapp_s13_techpoint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _welcomeText = MutableLiveData<String>().apply {
        value = "Con TechPoint encuentras los mejores lugares para comprar equipos de cómputo cerca a tu ubicación actual."
    }
    val welcomeText: LiveData<String> = _welcomeText
}
