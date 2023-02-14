package com.example.appnativa.views.utils

import androidx.lifecycle.MutableLiveData

class DataModel {
    companion object {
        val instance = DataModel()
    }

    var message = MutableLiveData<String>()

    var editTextMessageNative: String = ""
        set(value) {
            field = value
        }
        get() = field

    fun updateMessage(message: String) {
        this.message.postValue(message)
    }

    fun removeObserver() {
        message.removeObserver { }
    }

}