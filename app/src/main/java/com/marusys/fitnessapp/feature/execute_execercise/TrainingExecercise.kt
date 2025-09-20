package com.marusys.fitnessapp.feature.execute_execercise

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

// Exercise
@Composable
fun TrainingExerciseScreen(modifier: Modifier = Modifier){
//    val onEvent : (TrainingIntent) -> Unit = remember {
//            viewModel::processIntent
//    }
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("Full Body Warm Up", style = LocalMyTypography.current.h2BoldStyle)
        Spacer(Modifier.height(40.dp))
        YoutubePlayerVideo("c4DAnQ6DtF8", onLoadedVideo = {
//            onEvent(TrainingIntent.TimeoutWorking)
        }) { }
        Spacer(Modifier.height(40.dp))
        TimerCounterView(data = { "03:00" })
        Spacer(Modifier.height(32.dp))
        BuildButtonActionEx()

        Spacer(Modifier.height(20.dp))

    }
}

@Composable
fun BuildButtonActionEx() {
   Column(modifier = Modifier.fillMaxWidth(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ) {

           Box(modifier = Modifier
               .width(156.dp)
               .height(56.dp)
               .background(color = colorResource(R.color.white))
               .border(1.dp, shape =  RoundedCornerShape(16.dp), color = colorResource(R.color.primary))
               ,
               contentAlignment = Alignment.Center
           ){
               Text("Restart", style = LocalMyTypography.current.bodyBold, color = colorResource(R.color.primary))
           }
           Spacer(Modifier.width(40.dp))
           Box(modifier = Modifier
               .width(156.dp)
               .height(56.dp)
               .background(color = colorResource(R.color.primary), shape = RoundedCornerShape(16.dp))
               ,
               contentAlignment = Alignment.Center
           ){
               Text("Pause", style = LocalMyTypography.current.bodyBold, color = colorResource(R.color.white))
           }
       }
       Box(modifier = Modifier
           .width(156.dp)
           .height(56.dp)
           .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp))
           ,
           contentAlignment = Alignment.Center
       ){
           Text("Skip", style = LocalMyTypography.current.h3SemiboldStyle, color = colorResource(R.color.gray_1))
       }
   }
}

@Preview
@Composable
fun PreviewTrainingScreen(){
    FitnessAppTheme {
        TrainingExerciseScreen()
    }
}


@Composable
fun BottomResultNotificationScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth()
        .padding(horizontal = 24.dp)
        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(imageVector = ImageVector.vectorResource(R.drawable.thumb), "Thumb")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Great job! Workout completed", style = LocalMyTypography.current.h3BoldStyle)
        Spacer(modifier = Modifier.height(40.dp))
        Row (modifier = Modifier.fillMaxWidth().height(95.dp)
            .background(color = colorResource(R.color.bg_item_category), shape = RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("20", style = LocalMyTypography.current.h2BoldStyle)
                Text("Total Exercises", style = LocalMyTypography.current.bodyMedium, color = colorResource(R.color.gray_2))
            }
            Spacer(modifier = Modifier.width(34.dp))
            Box(modifier = Modifier.width(2.dp).height(63.dp).background(color = colorResource(R.color.gray_3)))
            Spacer(modifier = Modifier.width(34.dp))
            Column (modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("233", style = LocalMyTypography.current.h2BoldStyle)
                Text("Calories Burnt", style = LocalMyTypography.current.bodyMedium, color = colorResource(R.color.gray_2))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(modifier = Modifier.fillMaxWidth().height(56.dp)
            .background(color = colorResource(R.color.primary), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
            ) {
            Text("Next Challenge", style = LocalMyTypography.current.bodyBold, color = colorResource(R.color.white))
        }
    }
}

@Preview
@Composable
fun PreviewBottomResultNotification(){
    FitnessAppTheme {
        BottomResultNotificationScreen()
    }
}
@Composable
fun YoutubePlayerVideo(videoId: String, onLoadedVideo: () -> Unit,  onBack: () -> Unit){
    val TAG = "YoutubePlayerVideo"
    val context = LocalContext.current
    var playbackPosition by rememberSaveable { mutableFloatStateOf(0f) }
    val youTubePlayer = remember { YouTubePlayerView(context = context) }
    var youTubePlayerManager by remember { mutableStateOf<YouTubePlayer?>(null) }
    val youTubePlayerCallback = remember {
        object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Log.d(TAG, "onReady: Single instance")
                youTubePlayer.setVolume(0)
                youTubePlayer.loadVideo(videoId, playbackPosition)
            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                Log.d(TAG, "onStateChange: ${state.name}")
                if (state == PlayerConstants.PlayerState.PLAYING) {
                    if (youTubePlayerManager == null) {
                        Log.d(TAG, "First time player ready")
                        onLoadedVideo()
                        youTubePlayerManager = youTubePlayer
                    }
                }
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
               playbackPosition = second
            }
        }
    }

    DisposableEffect(Unit) {
        youTubePlayer.apply {
            addYouTubePlayerListener(youTubePlayerCallback)
        }
        onDispose {
            Log.d(TAG, "VideoPlayerBox: =====> onDispose")
            youTubePlayer.removeYouTubePlayerListener(youTubePlayerCallback)
            youTubePlayerManager?.removeListener(youTubePlayerCallback)
        }
    }


    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier
                .then(Modifier.fillMaxWidth())
                .aspectRatio(16f / 9f),
            factory = { youTubePlayer }
        )
    }
}

@Composable
fun TimerCounterView(data : () -> String ){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Text(data(), style = LocalMyTypography.current.h1Style)
    }
}