package com.marusys.fitnessapp.feature.loading

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import kotlinx.coroutines.delay

val backgroundVertical = Brush.verticalGradient(
    colors = listOf(
        Color(0x00FFFFFF),
        Color(0x00FFFFFF),
        Color(0x00FFFFFF),
        Color(0xFFFFFFFF),
    )
)

@Composable
fun LoadingBannerScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Box(modifier = Modifier.height(570.dp).fillMaxWidth()){
                Image(painter =
                    painterResource(R.drawable.img_onboarding), "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(modifier = Modifier.fillMaxSize().background(backgroundVertical))
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.wrapContentWidth()){
                Text("    Wherever you are\nhealth is number one",
                    style = LocalMyTypography.current.h2BoldStyle)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Text("There is no instant way to a healthy life", style = LocalMyTypography.current.body2Regular)
            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
            RoundedProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                progressColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RoundedProgressBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    height: Dp = 12.dp
) {
    var progress by rememberSaveable { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 1200),
        label = "progressAnimation"
    )

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            progress += 0.2f
            delay(1000)
        }
    }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val barWidth = size.width
        val barHeight = height.toPx()
        val radius = barHeight / 2f
        val progressWidth = barWidth * animatedProgress

        drawRoundRect(
            color = backgroundColor,
            size = Size(barWidth, barHeight),
            cornerRadius = CornerRadius(radius, radius),
            topLeft = Offset(0f, (size.height - barHeight) / 2f)
        )

        if (animatedProgress > 0) {
            drawRoundRect(
                color = progressColor,
                size = Size(progressWidth, barHeight),
                cornerRadius = CornerRadius(radius, radius),
                topLeft = Offset(0f, (size.height - barHeight) / 2f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewOnBoardingScreen() {
    LoadingBannerScreen()

}
