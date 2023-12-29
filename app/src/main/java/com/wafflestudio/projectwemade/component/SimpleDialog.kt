package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    title: String,
    onClickCancel: () -> Unit = {},
    onClickOK: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties()
    ) {
        Column(
            modifier = Modifier
                .background(color = WemadeColors.White900, shape = RoundedCornerShape(4.dp))
                .height(160.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Divider(thickness = 0.5.dp, color = WemadeColors.NormalGray)
            Row(
                modifier = Modifier.height(60.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            onClickCancel()
                        }
                ) {
                    Text(
                        text = "취소",
                        modifier = Modifier.align(Alignment.Center),
                        color = WemadeColors.Black900,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Divider(
                    color = WemadeColors.NormalGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(0.5.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            onClickOK()
                        }
                ) {
                    Text(
                        text = "확인",
                        modifier = Modifier.align(Alignment.Center),
                        color = WemadeColors.MainGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}