package com.marusys.fitnessapp.feature.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.component.ItemCategory
import com.marusys.fitnessapp.component.WorkoutItem
import com.marusys.fitnessapp.model.Category
import com.marusys.fitnessapp.model.typeExerciseList
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.H2ExtraBoldStyle
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import com.marusys.fitnessapp.ui.theme.MyTypograph


val backgroundLinearGradient = Brush.linearGradient(
    listOf(
        Color(0xFF9747FF),
        Color(0xFF6F00FF)
    )
)

@Composable
fun FitnessBanner(
    title: String,
    subtitle: String,
    onStartClick: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(176.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundLinearGradient)
    ) {

        val (vBox, imgBanner) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .constrainAs(vBox) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                },
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = colorResource(R.color.white),
                style = LocalMyTypography.current.h2ExtraBoldStyle,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onStartClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = stringResource(R.string.start_ex),
                    color = colorResource(R.color.primary),
                    style = LocalMyTypography.current.body2Bold
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.img_banner),
            contentDescription = "Fitness Person",
            modifier = Modifier
                .constrainAs(imgBanner) {
                    end.linkTo(parent.end, 14.dp)
                    bottom.linkTo(parent.bottom)
                }
                .width(126.dp)
                .height(135.dp)
                .padding(end = 4.dp),
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    val TAG = "HomeScreen"
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .background(colorResource(R.color.white))
            .padding(horizontal = 16.dp)

        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        item {
            Spacer(Modifier.height(9.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Flexio", style = LocalMyTypography.current.h3BoldStyle)
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.notification),
                    "notificaiton"
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            FitnessBanner(title =  stringResource(R.string.content_banner), "") { }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.progress), style = LocalMyTypography.current.h3BoldStyle)
                Text(
                    stringResource(R.string.see_all),
                    style = LocalMyTypography.current.body2Semi,
                    color = colorResource(R.color.primary)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(10) {
                    WorkoutItem(
                        title = "Jumping Jacks",
                        remainingTime = "2:30",
                        progress = 0.5f,
                        current = 5,
                        total = 10
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.category), style = LocalMyTypography.current.h3BoldStyle)
                Text(
                    stringResource(R.string.see_all),
                    style = LocalMyTypography.current.body2Semi,
                    color = colorResource(R.color.primary)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(typeExerciseList.size) {
            ItemCategory(data = typeExerciseList[it]) {}
        }
        item { Spacer(modifier = Modifier.height(48.dp)) }
    }

}

@Composable
fun ExerciseTotalView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(20.dp))
        ItemCategory(modifier = Modifier, data = typeExerciseList[0], color = colorResource(R.color.white)) { }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Exercises", style = LocalMyTypography.current.h3BoldStyle)
        LazyColumn(
            modifier = Modifier.height(126.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            items(10) {
                BuildItemExercise()
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Box(modifier = Modifier.fillMaxWidth().height(56.dp)
            .background(color = colorResource(R.color.primary), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Start Exercise", style = LocalMyTypography.current.bodyBold, color = colorResource(R.color.white))
        }
    }
}

@Composable
fun BuildItemExercise(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier.fillMaxWidth().height(20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .background(color = colorResource(R.color.gray_1), shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            "Jumping Jacks",
            style = LocalMyTypography.current.body2Regular,
            color = colorResource(R.color.gray_1)
        )
    }
}


@Preview
@Composable
fun PreviewExerciseTotalView() {
    FitnessAppTheme {
        ExerciseTotalView()
    }
}

@Composable
fun ItemCategoryExercise(
    isSelected: Boolean = false
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(37.dp)
            .drawBehind {
                drawRoundRect(
                    color = if (isSelected)
                        Color(context.getColor(R.color.primary)) else Color(
                        context.getColor(R.color.gray_3)
                    ),
                    style = Stroke(width = 1f),
                    size = size,
                    cornerRadius = CornerRadius(32f, 32f)
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Text(
            "All", color = if (isSelected)
                Color(context.getColor(R.color.primary)) else Color(
                context.getColor(R.color.gray_3)
            )
        )
    }
}

@Preview
@Composable
fun FitnessBannerPreview() {
    FitnessAppTheme {
        CompositionLocalProvider(LocalMyTypography provides MyTypograph()) {
//            HomeScreen(modifier = Modifier.fillMaxSize())
        }
    }
}