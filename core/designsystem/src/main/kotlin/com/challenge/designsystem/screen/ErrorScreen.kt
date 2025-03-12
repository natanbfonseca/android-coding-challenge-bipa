package com.challenge.designsystem.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.challenge.designsystem.R

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, text: String?) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text ?: stringResource(R.string.something_went_wrong),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_9_pro_xl", showSystemUi = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        text = stringResource(R.string.something_went_wrong)
    )
}