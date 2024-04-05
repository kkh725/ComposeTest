package com.example.composetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // LiveData로 관리할 데이터
    private val _myData = MutableLiveData<String>()
    val myData: LiveData<String> get() = _myData

    init {
        // 초기 데이터 설정
        _myData.value = "Initial Data"
    }

    // 데이터 변경 함수
    fun updateData(newData: String) {
        _myData.value = newData
    }
}
