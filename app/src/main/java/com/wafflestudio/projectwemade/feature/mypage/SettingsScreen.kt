package com.wafflestudio.projectwemade.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.icon.LogoutIcon
import com.wafflestudio.projectwemade.icon.ProfileAddPhotoIcon
import com.wafflestudio.projectwemade.icon.RightArrow
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun SettingsScreen(){
    val navController = LocalNavController.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterTopBar(
            title = "설정",
            leftAction = {
                LeftArrow(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.popBackStack() }
                )
            }
        )
        Divider(
            thickness = 1.dp,
            color = WemadeColors.LightGray
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .background(WemadeColors.ExtraLightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            ProfileAddPhotoIcon(
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Divider(
                thickness = 1.dp,
                color = WemadeColors.LightGray
            )
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row (
                    modifier = Modifier.align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LogoutIcon(
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "회원탈퇴",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "고객정보가 모두 삭제됩니다.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = WemadeColors.DarkGray
                        )
                    }
                }
                RightArrow(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Divider(
                thickness = 1.dp,
                color = WemadeColors.LightGray
            )
        }
    }
}