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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.icon.AddIcon
import com.wafflestudio.projectwemade.icon.DownArrow
import com.wafflestudio.projectwemade.theme.WemadeColors
import okio.utf8Size

@Composable
fun ContactScreen() {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var contactContent by remember { mutableStateOf("") }
    var selectedOption: ContactOption? by remember { mutableStateOf(null) }
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
            Box {
                Row(
                    modifier = Modifier
                        .clickable {
                            isMenuExpanded = !isMenuExpanded
                        }
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
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedOption?.toString() ?: "문의 유형을 선택해 주세요.",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedOption != null) WemadeColors.Black900 else WemadeColors.MediumGray,
                    )
                    DownArrow(
                        modifier = Modifier
                            .size(10.dp)
                    )
                }
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    modifier = Modifier
                        .background(WemadeColors.White900)
                        .fillMaxWidth()
                ) {
                    ContactOption.values().forEach {
                        Row(
                            modifier = Modifier
                                .background(WemadeColors.White900)
                                .clickable {
                                    selectedOption = it
                                    isMenuExpanded = false
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = it.toString(),
                                color = WemadeColors.Black900,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 200.dp)
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
                BasicTextField(
                    value = contactContent,
                    onValueChange = {
                        if (contactContent.utf8Size() < 500) contactContent = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    decorationBox = {
                        it()
                    }
                )
                if (contactContent == "") {
                    Text(
                        text = "문의 내용을 입력해주세요.(500자 이내)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = WemadeColors.MediumGray,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
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
            enabled = (contactContent != "" && selectedOption != null)
        )
    }
}

@Composable
fun ContactsPhoto(
    modifier: Modifier = Modifier
) {
    Box(
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

@Preview
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}