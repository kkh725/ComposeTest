package com.example.composetest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // LiveData로 관리할 데이터
    private val _myData = mutableStateOf<String>("") //수정 가능한 데이터상태이며 뷰모델에서만 수정이가능하다.
    val myData: State<String> = _myData //읽기 전용으로 view에서 공개된다.

    init {
        // 초기 데이터 설정
        _myData.value = "Initial Data"
    }

    // 데이터 변경 함수
    fun updateData(newData: String) {
        _myData.value = newData
    }
}