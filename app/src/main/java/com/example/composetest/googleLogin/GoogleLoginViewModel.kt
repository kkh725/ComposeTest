package com.example.composetest.googleLogin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GoogleLoginViewModel : ViewModel() {
    private val _isFailState = mutableStateOf(false)
    val isFailState : State<Boolean> = _isFailState

//    fun login(
//        activityResult: Instrumentation.ActivityResult,
//        onSuccess: () -> Unit
//    ) {
//        try {
//            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
//                .getResult(ApiException::class.java)
//            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//            FirebaseAuth.getInstance().signInWithCredential(credential)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) onSuccess()
//                }
//        } catch (e: Exception) {
//            _isFailState.value = true
//        }
//    }

}