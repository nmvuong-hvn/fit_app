package com.marusys.fitnessapp.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marusys.fitnessapp.R

@Composable
fun FitnessBanner(
    title: String,
    subtitle: String,
    onStartClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF8A4FFF)) // tím giống hình
            .padding(16.dp)
    ) {
        // Nội dung text bên trái
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(0.6f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onStartClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Start Exercise",
                    color = Color(0xFF8A4FFF),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Hình nhân vật bên phải
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // thay bằng ảnh thật của bạn
            contentDescription = "Fitness Person",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(120.dp)
                .padding(end = 4.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun FitnessBannerPreview() {
    FitnessBanner(
        title = "Ready for your next workout?",
        subtitle = "Let's get moving!",
        onStartClick = {}
    )
}