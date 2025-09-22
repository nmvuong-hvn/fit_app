package com.marusys.fitnessapp.feature.account

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.marusys.fitnessapp.model.User
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpAccountScreen(
    modifier: Modifier = Modifier, viewModel: AccountViewModel,
    onNavigate: () -> Unit,
) {
    val TAG = "SignUpAccountScreen"
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val onEventSignUp: (AccountIntent) -> Unit = remember {
        viewModel::processIntent
    }

    LaunchedEffect(viewModel.accountEvent) {
        viewModel.accountEvent.collectLatest {
            when (it) {
                is AccountEvent.Navigate -> {
                }

                AccountEvent.None -> {}
                is AccountEvent.ToastMessage<*> -> {
                    Log.d(TAG, "SignUpAccountScreen: ====> VAO it = $it")
                    if (it.message is AccountRepoState<*>) {
                        Toast.makeText(context, it.message.message, Toast.LENGTH_SHORT).show()
                        delay(500L)
                        if (it.message.isSuccess == true) {
                            onEventSignUp(AccountIntent.ClearData)
                            onNavigate()
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.full_name), style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { fullName },
            onValueChange = { fullName = it },
            placeHolder = { stringResource(R.string.full_name) })
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                stringResource(R.string.email_address),
                style = LocalMyTypography.current.bodyMedium
            )
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(data = {
            email
        }, onValueChange = { email = it }, placeHolder = { stringResource(R.string.email_address) })
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.pass), style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { password },
            onValueChange = { password = it },
            placeHolder = { stringResource(R.string.pass) },
            isPassword = { true })
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                stringResource(R.string.confirm_pass),
                style = LocalMyTypography.current.bodyMedium
            )
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { confirmPassword },
            onValueChange = { confirmPassword = it },
            placeHolder = { stringResource(R.string.confirm_pass) },
            isPassword = { true })
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                val user = User("", fullName, email)
                user.setPassword(password)
                user.setConfirmPassword(confirmPassword)
                onEventSignUp(AccountIntent.SignUp(user))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
        ) {
            Text(
                stringResource(R.string.sign_up),
                color = Color.White,
                style = LocalMyTypography.current.bodyBold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PreviewSignUpAccountScreen() {
    FitnessAppTheme {
        SignUpAccountScreen(viewModel = koinViewModel<AccountViewModel>()) {}
    }
}