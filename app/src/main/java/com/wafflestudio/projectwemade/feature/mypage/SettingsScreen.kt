package com.wafflestudio.projectwemade.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.icon.CalendarIcon
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.icon.ListIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun SettingsScreen() {
    val selectedOption = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WemadeColors.ExtraLightGray)
    ) {
        CenterTopBar(
            title = "주문내역",
            modifier = Modifier
                .fillMaxWidth()
                .background(WemadeColors.White900),
            leftAction = {
                LeftArrow(modifier = Modifier.size(32.dp))
            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(WemadeColors.LightGray))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateSelector(selectedOption)
            if (selectedOption.value == "기간설정") {
                Column(
                    modifier = Modifier.background(WemadeColors.LightGray),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    OrderHistoryCard()
                    OrderHistoryCard()
                    OrderHistoryCard()
                }
            }
            else {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ListIcon(
                            modifier = Modifier.size(64.dp),
                            color = WemadeColors.LightGray
                        )
                        Spacer(modifier = Modifier.height(60.dp))
                        Text(
                            text = "해당 기간 주문 내역이 없습니다.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "기간 설정을 통해 주문 내역을 확인해보세요.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = WemadeColors.MediumGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DateSelector(
    selectedOption: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(
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
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            DateComponent(modifier = Modifier.weight(1f))
            Text(text = "~")
            DateComponent(modifier = Modifier.weight(1f))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TimePeriodComponent(
                optionName = "1개월",
                selectedValue = selectedOption
            )
            TimePeriodComponent(
                optionName = "1년",
                selectedValue = selectedOption
            )
            TimePeriodComponent(
                optionName = "기간설정",
                selectedValue = selectedOption
            )
        }
    }
}

@Composable
fun DateComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = WemadeColors.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = "YYYY.MM.DD",
            style = MaterialTheme.typography.bodyLarge,
            color = WemadeColors.DarkGray,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        CalendarIcon(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun TimePeriodComponent(
    optionName: String,
    selectedValue: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = optionName == selectedValue.value,
            onClick = {selectedValue.value = optionName},
            colors = RadioButtonDefaults.colors(
                selectedColor = WemadeColors.MainGreen,
                unselectedColor = WemadeColors.MediumGray
            ),
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = optionName,
            style = MaterialTheme.typography.bodyMedium,
            color = WemadeColors.DarkGray
        )
    }
}

@Composable
fun OrderHistoryCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(WemadeColors.White900)
            .padding(horizontal = 12.dp, vertical = 20.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(WemadeColors.LightGray)
            )
            Column (
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "상품이름",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "(HOT/중간/일회용컵)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = WemadeColors.MediumGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "주문일시 : YYYY.MM.DD 오후 TT.MM",
                    style = MaterialTheme.typography.bodyMedium,
                    color = WemadeColors.MediumGray
                )
            }
        }
        Text(
            text = "1개",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview(){
    SettingsScreen()
}