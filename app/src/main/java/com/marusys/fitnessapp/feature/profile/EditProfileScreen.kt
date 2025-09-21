package com.marusys.fitnessapp.feature.profile

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.feature.account.AccountEvent
import com.marusys.fitnessapp.feature.account.AccountIntent
import com.marusys.fitnessapp.feature.account.AccountViewModel
import com.marusys.fitnessapp.feature.account.BuildUiInputText
import com.marusys.fitnessapp.model.User
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier, viewModel : AccountViewModel){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val TAG = "EditProfileScreen"
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val onEventSignUp : (AccountIntent) -> Unit = remember {
        viewModel::processIntent
    }

    LaunchedEffect(viewModel.accountEvent) {
        viewModel.accountEvent.collectLatest {
            when (it) {
                is AccountEvent.Navigate -> {
                    //onNavigate()
                }

                AccountEvent.None -> {}
                is AccountEvent.Toast -> {
                    Log.d(TAG, "SignUpAccountScreen: ====> VAO")
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White )
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text("Full name", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { fullName },
            onValueChange = { fullName = it },
            placeHolder = { "Fullname" })
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text("Email Address", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(data = {
            email
        }, onValueChange = { email = it }, placeHolder = { "Email Address" })
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text("Phone", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { phone },
            onValueChange = { phone = it },
            placeHolder = { "Phone" },)
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text("Address", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            data = { address },
            onValueChange = { address = it },
            placeHolder = { "Address" },
         )
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                val user = User("", fullName, email, phone, address)

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
        ) {
            Text("Update", color = Color.White, style = LocalMyTypography.current.bodyBold)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen(){
    FitnessAppTheme {
        EditProfileScreen(
            viewModel = koinViewModel()
        )
    }
}