package com.marusys.fitnessapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.model.Category

@Composable
fun ItemCategory(modifier: Modifier = Modifier, data : Category, onClick : (Category) -> Unit){
    Box(modifier = modifier
        .height(81.dp)
        .fillMaxWidth()
        .background(colorResource(R.color.bg_item_category), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.CenterStart
    ){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(painter = painterResource(R.drawable.ic_launcher_background), "",
                modifier = Modifier.size(64.dp, 32.dp)
                )
            Column(modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
                ) {
                Text("Full Body Warm Up")
                Spacer(modifier = Modifier.height(10.dp))
                Text("20 Exercises")
            }
        }
    }
}

@Composable
fun ItemProgressCategory(modifier: Modifier = Modifier){
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
    }
}


@Composable
fun WorkoutItem(
    title: String,
    remainingTime: String,
    progress: Float, // từ 0f -> 1f
    current: Int,
    total: Int
) {
    Box(
        modifier = Modifier
            .width(144.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF6F8FF))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Vòng tròn progress
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = progress,
                    strokeWidth = 6.dp,
                    strokeCap = StrokeCap.Round,
                    color = Color(0xFF8A4FFF), // tím
                    trackColor = Color(0xFFE5E7EB),
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "$current/$total",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8A4FFF)
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tên bài tập
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )

            // Thời gian còn lại
            Text(
                text = remainingTime,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }
    }
}
@Preview
@Composable
fun PreviewWorkoutItem() {
    WorkoutItem(
        title = "Jumping Jacks",
        remainingTime = "2:30",
        progress = 0.5f,
        current = 5,
        total = 10
    )
}




@Preview
@Composable
fun PreviewItemCategory(){
    ItemCategory(data = Category()){}
}

