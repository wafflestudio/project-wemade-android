package com.wafflestudio.projectwemade.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.common.CtaButton
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.component.Checkbox
import com.wafflestudio.projectwemade.component.Chip
import com.wafflestudio.projectwemade.component.NumericStepper
import com.wafflestudio.projectwemade.component.OptionChip
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeScreen() {
    val bottomSurfaceState = LocalBottomSurfaceState.current
    var selectedChip by remember { mutableStateOf("커피") }
    var selectedOption by remember { mutableStateOf("기본") }
    var checked by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            listOf("커피", "티", "에이드", "밀크", "디카페인", "빙수", "식사").forEach {
                Chip(
                    text = it,
                    selected = it == selectedChip,
                    onClick = {
                        selectedChip = it
                    }
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            listOf("기본", "진하게", "연하게").forEach {
                OptionChip(
                    text = it,
                    selected = it == selectedOption,
                    onClick = {
                        selectedOption = it
                    }
                )
            }
        }
        Text(
            text = "Home screen",
            modifier = Modifier
        )
        CtaButton(
            text = "show bottomsurface",
            onClick = {
                bottomSurfaceState.content = {
                    Text(
                        text = "hello!!!",
                        modifier = Modifier.height(100.dp)
                    )
                }
                bottomSurfaceState.visible = bottomSurfaceState.visible.not()
            }
        )
        CtaButton(
            text = "disabled",
            onClick = {
                bottomSurfaceState.content = {
                    Text(
                        text = "hello!!!",
                        modifier = Modifier.height(100.dp)
                    )
                }
                bottomSurfaceState.visible = bottomSurfaceState.visible.not()
            },
            enabled = false,
        )
        Checkbox(
            checked = checked,
            onCheckChanged = { checked = it },
            modifier = Modifier.size(40.dp)
        )
        NumericStepper(
            value = number,
            onValueChanged = { number = it },
            maxValue = 5,
        )
    }
}