package com.wafflestudio.projectwemade.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.icon.ListIcon
import com.wafflestudio.projectwemade.icon.ProfileRoundIcon
import com.wafflestudio.projectwemade.icon.SettingsIcon
import com.wafflestudio.projectwemade.icon.SupportIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun MypageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            CenterTopBar(
                title = "마이페이지"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(WemadeColors.LightGray)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ProfileRoundIcon(
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                )
                Text(
                    text = "OOO님,\n안녕하세요!",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                MypageOptionCard(
                    icon = { ListIcon(modifier = Modifier.size(36.dp)) },
                    label = "주문내역"
                )
                MypageOptionCard(
                    icon = { SupportIcon(modifier = Modifier.size(36.dp)) },
                    label = "고객센터"
                )
                MypageOptionCard(
                    icon = { SettingsIcon(modifier = Modifier.size(36.dp)) },
                    label = "설정"
                )
            }
        }
        Text(
            text = "로그아웃",
            modifier = Modifier.padding(bottom = 30.dp).align(Alignment.BottomCenter),
            style = MaterialTheme.typography.bodyMedium,
            color = WemadeColors.DarkGray,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview
@Composable
fun MypageScreenPreview() {
    MypageScreen()
}