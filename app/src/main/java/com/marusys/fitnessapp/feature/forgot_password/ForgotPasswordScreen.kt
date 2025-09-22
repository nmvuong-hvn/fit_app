package com.marusys.fitnessapp.feature.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.feature.account.BuildUiInputText
import com.marusys.fitnessapp.ui.theme.LocalMyTypography

@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit,
    onResetPassword: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Subtitle
        Text(
            text = "Please enter your email to reset the password",
            style = LocalMyTypography.current.h3BoldStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Label
        Text(
            text = stringResource(R.string.email_address),
            style = LocalMyTypography.current.bodyMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))
        BuildUiInputText(data = {""},
            placeHolder = { stringResource(R.string.email_address) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onResetPassword(email)
                }
                .background(colorResource(R.color.primary), shape = RoundedCornerShape(12.dp))
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Reset Password", style = LocalMyTypography.current.bodyBold, color = Color.White)
        }
    }
}

@Preview
@Composable
fun PreviewForgotPasswordScreen() {
    ForgotPasswordScreen(onBackClick = {}, onResetPassword = {})
}
