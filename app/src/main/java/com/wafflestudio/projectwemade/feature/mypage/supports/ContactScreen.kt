package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.icon.AddIcon
import com.wafflestudio.projectwemade.icon.DownArrow
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
    val isMenuExpanded = remember{ mutableStateOf(false) }
    var contactContent by remember{ mutableStateOf("") }
    val selectedOption = remember{ mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.ExtraLightGray)
            .padding(top = 18.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
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
                        isMenuExpanded.value = !isMenuExpanded.value
                    }
            ) {
                if(selectedOption.value != "") {
                    Text(
                        text = selectedOption.value,
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
                DownArrow(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            if(isMenuExpanded.value){

                Dialog(onDismissRequest = { isMenuExpanded.value = false }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        color = WemadeColors.White900
                    ) {
                        OptionDialog(selectedOption, isMenuExpanded)
                    }
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
                    .defaultMinSize(minHeight = 200.dp),
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
                        if(contactContent == "") {
                            Text(
                                text = "문의 내용을 입력해주세요.(500자 이내)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = WemadeColors.MediumGray
                            )
                        }
                        else {
                            it()
                        }
                        Row(
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            Text(
                                text = "(",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = contactContent.utf8Size().toString(),
                                color = WemadeColors.MainGreen,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "/500)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "사진등록",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "최대 2장까지 등록 가능합니다.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ContactsPhoto(modifier = Modifier.size(80.dp))
                ContactsPhoto(modifier = Modifier.size(80.dp))
            }
        }
        CtaButton(
            text = "문의하기",
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = (contactContent != "" && selectedOption.value != "")
        )
    }
}

@Composable
fun ContactsPhoto(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .background(
                color = WemadeColors.White900,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = WemadeColors.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        AddIcon(
            color = WemadeColors.DarkGray,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun OptionDialog(
    selectedOption: MutableState<String>,
    isMenuExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        contactOptions.forEach(){
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = WemadeColors.Black900,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .clickable {
                        selectedOption.value = it
                        isMenuExpanded.value = false
                    }
            )
        }
    }
}


@Preview
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}