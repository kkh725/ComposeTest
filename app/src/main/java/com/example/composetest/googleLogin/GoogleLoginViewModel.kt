package com.example.composetest.googleLogin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.composetest.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleLoginViewModel : ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth
    var state = mutableStateOf(false)

    fun signInWithGoogle(
        launcher: ManagedActivityResultLauncher<Intent,androidx.activity.result.ActivityResult>,
        context: Context
    ){
        val token = BuildConfig.google_native_api_key

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    fun handleGoogleSignInResult(data: Intent?, navController: NavController) {
        val account = GoogleSignIn.getSignedInAccountFromIntent(data)
            .getResult(ApiException::class.java)

        val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = true
                    // 로그인 성공
                    navController.navigate("screen2")
                    Log.d("TAG", "Google Sign In Success")
                    Log.d(TAG, "firebaseAuthWithGoogle id:" + account.id)
                    Log.d(TAG, "firebaseAuthWithGoogle idtoken:" + account.idToken) //이 토큰을 주로 식별용으로 사용할것.
                    Log.d(TAG, "firebaseAuthWithGoogle current user:" + auth.currentUser!!.displayName.toString()) //로그인 되어있을때 사용. 아니면 null
                    Log.d(TAG, "firebaseAuthWithGoogle current user:" + auth.currentUser!!.photoUrl.toString()) //로그인 시 이메일인증
                    Log.d(TAG, "firebaseAuthWithGoogle user:" + task.result.user!!.displayName.toString()) //구글 로그인 할때의 정보를 가져오는듯?
                    Log.d(TAG, "firebaseAuthWithGoogle credential :" + task.result.credential.toString())

                    // 추가 작업 수행
                } else {
                    // 로그인 실패
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // 실패 처리 작업 수행
                }
            }
    }



}

@Composable
fun GoogleLoginSuccessDialog(dialogState: MutableState<Boolean>){
    AlertDialog(
        onDismissRequest = { dialogState.value = false},
        confirmButton = {
            Button(onClick = { dialogState.value = false },) {
                Text(text = "확인")
            }
        },
        text = { Text(text = "구글 로그인 성공!") }
    )
}