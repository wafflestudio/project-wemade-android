package com.wafflestudio.projectwemade.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.LoginTextField
import com.wafflestudio.projectwemade.icon.LogoIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun SignInScreen() {
    val navController = LocalNavController.current
    var id by remember { mutableStateOf(TextFieldValue("")) }
    var pw by remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
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
                Text (
                    text = "어서오세요, 그린텀블러입니다.",
                    style = MaterialTheme.typography.titleLarge
                )
                Text (
                    text = "주문 서비스 이용을 위해 로그인해 주세요.",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column (
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LoginTextField(
                    value = id,
                    onValueChange = {newId -> id = newId},
                    placeholderString = "사원번호(예:20230508)"
                )
                LoginTextField(
                    value = pw,
                    onValueChange = {newPw -> pw = newPw},
                    placeholderString = "비밀번호"
                )
            }
        }
        CtaButton(
            text = "로그인",
            onClick = { navController.navigate(NavigationRoutes.MAIN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        )
    }
}