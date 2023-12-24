package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.icon.DownArrow
import com.wafflestudio.projectwemade.icon.UpArrow
import com.wafflestudio.projectwemade.theme.WemadeColors
import okio.utf8Size

private val contactOptions = listOf(
    "이용문의",
    "서비스 불편 신고",
    "건의사항",
    "주문관련",
    "기타"
)

@Composable
fun ContactScreen() {
    var isMenuExpanded by remember{ mutableStateOf(false) }
    var contactContent by remember{ mutableStateOf("") }
    var selectedOption by remember{ mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.ExtraLightGray)
            .padding(top = 18.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = WemadeColors.White900,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = WemadeColors.LightGray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp)
                    .clickable {
                        isMenuExpanded = !isMenuExpanded
                    }
            ) {
                if(selectedOption != "") {
                    Text(
                        text = selectedOption,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
                else {
                    Text(
                        text = "문의 유형을 선택해 주세요.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = WemadeColors.MediumGray,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
                if(isMenuExpanded) {
                    UpArrow(
                        modifier = Modifier
                            .size(10.dp)
                            .align(Alignment.CenterEnd)
                    )
                } else {
                    DownArrow(
                        modifier = Modifier
                            .size(10.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                contactOptions.forEach(){
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(20.dp)
                            )
                        },
                        onClick = {
                            selectedOption = it
                            isMenuExpanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = contactContent,
                onValueChange = {
                    if (contactContent.utf8Size() < 500) contactContent = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                decorationBox = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = WemadeColors.White900,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = WemadeColors.LightGray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(16.dp)
                    ) {
                        if(contactContent == "")
                            Text(
                                text = "문의 내용을 입력해주세요.(500자 이내)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = WemadeColors.MediumGray
                            )
                        else it()
                    }
                }
            )
        }


        CtaButton(
            text = "문의하기",
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}