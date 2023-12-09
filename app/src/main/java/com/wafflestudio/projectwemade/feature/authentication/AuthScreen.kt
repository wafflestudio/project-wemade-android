package com.wafflestudio.projectwemade.feature.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.util.launchApi

//로그인 기능 테스트하려고 만든 화면
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val user by authViewModel.user.collectAsState()

    Column {
        TextField(
            value = username,
            onValueChange = {
                username = it
            }
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            }
        )
        Text(
            text = user?.username ?: "not signed in"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "sign up",
            modifier = Modifier.clickable {
                launchApi(
                    context = context,
                    onSuccess = {
                        Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    authViewModel.signUp(
                        username = username,
                        password = password
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "sign in",
            modifier = Modifier.clickable {
                launchApi(
                    context = context,
                    onSuccess = {
                        Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    authViewModel.signIn(
                        username = username,
                        password = password,
                    )
                }
            }
        )
    }
}