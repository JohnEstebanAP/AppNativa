package com.example.appnativa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DataModel {
    companion object {
        val instance = DataModel()
    }

    var _message = MutableLiveData<String>()

    fun updateMessage(message: String) {
        _message.postValue(message)
    }

    fun removeObserver() {
        _message.removeObserver { }
    }

}