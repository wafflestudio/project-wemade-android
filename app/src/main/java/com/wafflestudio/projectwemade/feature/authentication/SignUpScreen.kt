package com.wafflestudio.projectwemade.feature.authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.Checkbox
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.LoginTextField
import com.wafflestudio.projectwemade.icon.LogoIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var pwCheck by remember { mutableStateOf("") }
    var privacyChecked by remember { mutableStateOf(false) }

    val handleSignUp = {
        if (privacyChecked) {
            authViewModel.signUp(
                username = id,
                password = pw,
                passwordConfirm = pwCheck,
                onUsernameDuplicated = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_duplicated_username),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onPasswordInsecure = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_insecure_password),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onPasswordTypo = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_password_typo),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onSuccess = {
                    navController.popBackStack()
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900)
            .padding(start = 20.dp, end = 20.dp, top = 60.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            LogoIcon(
                modifier = Modifier
                    .width(60.dp)
                    .height(108.dp)
                    .align(Alignment.CenterHorizontally),
                color = WemadeColors.MainGreen
            )
            Spacer(modifier = Modifier.height(76.dp))
            Column(
                modifier = Modifier.align(Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "어서오세요, 그린텀블러입니다.",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "주문 서비스 이용을 위해 회원가입해 주세요.",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LoginTextField(
                    value = id,
                    onValueChange = { newId -> id = newId },
                    hint = "사원번호(예:20230508)",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down)})
                )
                LoginTextField(
                    value = pw,
                    onValueChange = { newPw -> pw = newPw },
                    hint = "비밀번호",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down)})
                )
                LoginTextField(
                    value = pwCheck,
                    onValueChange = { newPwCheck -> pwCheck = newPwCheck },
                    hint = "비밀번호 확인",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onDone = { handleSignUp() })
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(WemadeColors.White900)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Checkbox(
                    checked = privacyChecked,
                    onCheckChanged = { privacyChecked = it },
                    modifier = Modifier
                        .align(Alignment.Top)
                        .size(24.dp)
                )
                Column {
                    Text(
                        text = "개인정보 수집 및 이용약관 동의 [필수]",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "이용약관을 동의하셔야 회원가입이 가능합니다.",
                        style = MaterialTheme.typography.bodySmall,
                        color = WemadeColors.DarkGray
                    )
                }
            }
            CtaButton(
                text = "회원가입",
                onClick = {
                    handleSignUp()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}