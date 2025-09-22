package com.marusys.fitnessapp.feature.forgot_password

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.feature.account.AccountEvent
import com.marusys.fitnessapp.feature.account.AccountIntent
import com.marusys.fitnessapp.feature.account.AccountRepoState
import com.marusys.fitnessapp.feature.account.AccountViewModel
import com.marusys.fitnessapp.feature.account.BuildUiInputText
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ForgotPasswordScreen(viewModel: AccountViewModel, onBackScreen: () -> Unit) {
    var email by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val onIntent: (AccountIntent) -> Unit = remember {
        viewModel::processIntent
    }
    LaunchedEffect(viewModel.accountEvent) {
        viewModel.accountEvent.collectLatest {
            when (it) {
                AccountEvent.Navigate -> {}
                AccountEvent.None -> {}
                is AccountEvent.ToastMessage<*> -> {
                    if (it.message is AccountRepoState<*>) {
                        Toast.makeText(context, it.message.message, Toast.LENGTH_SHORT).show()
                        delay(500L)
                        if (it.message.isSuccess == true) {
                            onBackScreen()
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.content_forgot_pass),
            style = LocalMyTypography.current.h3BoldStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.email_address),
            style = LocalMyTypography.current.bodyMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))
        BuildUiInputText(
            data = { email },
            placeHolder = { stringResource(R.string.email_address) },
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onIntent(AccountIntent.ForgotPassword(email))
                }
                .background(colorResource(R.color.primary), shape = RoundedCornerShape(12.dp))
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Reset Password",
                style = LocalMyTypography.current.bodyBold,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewForgotPasswordScreen() {
}
