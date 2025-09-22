

package com.marusys.fitnessapp.feature.profile

import android.app.PendingIntent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier, viewModel: ProfileViewModel,
    paddingValues: PaddingValues,
    onNavigate: (ProfileEvent) -> Unit,
) {
    val TAG = "ProfileScreen"
    val onProfileEvent : (ProfileIntent) -> Unit = remember {
        viewModel::processIntent
    }
    LaunchedEffect(viewModel.eventSetting) {
        viewModel.eventSetting.collectLatest {
            when(it){
                ProfileEvent.Logout -> {
                    onProfileEvent(ProfileIntent.ClearData)
                    onNavigate(ProfileEvent.Logout)
                }

                ProfileEvent.NavigateChangePassword -> {
                    onProfileEvent(ProfileIntent.ClearData)
                    onNavigate(ProfileEvent.NavigateChangePassword)
                }

                ProfileEvent.NavigateEditProfile -> {
                    onProfileEvent(ProfileIntent.ClearData)
                    onNavigate(ProfileEvent.NavigateEditProfile)
                }

                ProfileEvent.NavigateHelp -> {
                    onProfileEvent(ProfileIntent.ClearData)
                    onNavigate(ProfileEvent.NavigateHelp)
                }

                ProfileEvent.NavigateLanguage -> {
                    onProfileEvent(ProfileIntent.ClearData)
                    onNavigate(ProfileEvent.NavigateLanguage)
                }

                ProfileEvent.None -> {}
            }
        }
    }

    Log.d(TAG, "ProfileScreen: =====> ProfileUI")
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = com.marusys.fitnessapp.R.drawable.ic_launcher_background), // ảnh của bạn
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    // Icon edit nhỏ
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF9B51E0))
                            .padding(6.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile Picture",
                            tint = Color.White,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Ronald Richards",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Marketing Coordinator",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Card chứa các tùy chọn
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = colorResource(R.color.bg_item_category))
                    .padding(vertical = 8.dp)
            ) {
                ProfileMenuItem(icon = Icons.Default.Edit, title = stringResource(R.string.edit_profile)){
                    onProfileEvent(ProfileIntent.NavigateEditProfile)
                }
                HorizontalDivider()
                ProfileMenuItem(icon = Icons.Default.Lock, title = stringResource(R.string.change_password)){
                    onProfileEvent(ProfileIntent.NavigateChangePassword)

                }
                HorizontalDivider()
                ProfileMenuItem(icon = Icons.Default.Share, title = stringResource(R.string.help)){
                    onProfileEvent(ProfileIntent.NavigateHelp)
                }
                HorizontalDivider()
                ProfileMenuItem(icon = Icons.Default.Settings, title = stringResource(R.string.language)){
                    onProfileEvent(ProfileIntent.NavigateLanguage)
                }
                HorizontalDivider()
                ProfileMenuItem(icon = Icons.Default.ExitToApp, title = stringResource(R.string.logout)){
                    onProfileEvent(ProfileIntent.Logout)
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, onClick : () -> Unit) {

    val onEventState = remember {
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    }

    Row(
        modifier = onEventState
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFF3E8FF)), // nền tím nhạt cho icon
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFF9B51E0))
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = LocalMyTypography.current.bodyRegular,
            modifier = Modifier.fillMaxWidth(fraction = 0.9f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    FitnessAppTheme {
//          ProfileScreen()
    }
}
