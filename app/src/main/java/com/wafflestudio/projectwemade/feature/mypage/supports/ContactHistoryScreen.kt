package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.icon.DownArrow
import com.wafflestudio.projectwemade.icon.ListIcon
import com.wafflestudio.projectwemade.icon.UpArrow
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun ContactHistoryScreen(
    cardNum: Int
) {
    val cardOpened = List(cardNum) { mutableStateOf(false) }
    if (cardNum != 0) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            cardOpened.forEach {
                item { ContactHistoryCard(isOpened = it) }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(WemadeColors.ExtraLightGray)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ListIcon(
                    modifier = Modifier.size(64.dp),
                    color = WemadeColors.LightGray
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "문의 내역이 없습니다.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ContactHistoryCard(
    isOpened: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clickable { isOpened.value = !isOpened.value }
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = WemadeColors.MainGreen,
                                shape = CircleShape
                            )
                    )
                    Text(
                        text = "YYYY.MM.DD.TT:MM",
                        style = MaterialTheme.typography.bodyMedium,
                        color = WemadeColors.DarkGray
                    )
                }
                Text(
                    text = "유저의 문의내용...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if (isOpened.value) {
                UpArrow(
                    modifier = Modifier
                        .size(12.dp)
                )
            } else {
                DownArrow(
                    modifier = Modifier
                        .size(12.dp)
                )
            }
        }
        AnimatedVisibility(isOpened.value) {
            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .border(
                            width = 1.dp,
                            color = WemadeColors.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = "답변제목",
                        style = MaterialTheme.typography.bodyMedium,
                        color = WemadeColors.DarkGray
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .defaultMinSize(minHeight = 120.dp)
                        .border(
                            width = 1.dp,
                            color = WemadeColors.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = "답변내용",
                        style = MaterialTheme.typography.bodyMedium,
                        color = WemadeColors.DarkGray
                    )
                }
            }
        }
        Divider(
            thickness = 1.dp,
            color = WemadeColors.LightGray
        )
    }
}