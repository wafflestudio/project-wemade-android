package com.wafflestudio.projectwemade.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.icon.LogoIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(WemadeColors.MainGreen)
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        ) {
            LogoIcon(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .scale(0.7f)
                    .offset(x = (20).dp),
                color = WemadeColors.White900
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "그린텀블러",
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CreditCard(
                    role = "PM",
                    names = listOf("김은혜", "이선재"),
                    modifier = Modifier.weight(1f)
                )
                CreditCard(
                    role = "Client",
                    names = listOf("송동엽", "심우진"),
                    modifier = Modifier.weight(1f)
                )
                CreditCard(
                    role = "Design",
                    names = listOf("민유진"),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun CreditCard(
    role: String,
    names: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(110.dp)
            .border(
                width = 0.5.dp,
                color = WemadeColors.MediumGray,
                shape = RoundedCornerShape(5.dp)
            )
            .background(color = WemadeColors.White900, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 10.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = role,
            color = WemadeColors.Black900,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            names.forEach {
                Text(
                    text = it,
                    color = WemadeColors.DarkGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}