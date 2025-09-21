package com.marusys.fitnessapp.feature.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.LocalMyTypography

@Composable
fun SignInEmailScreen(
    onContinue: (String) -> Unit,
    onCreateAccount: () -> Unit,
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Sign In", style = LocalMyTypography.current.h2BoldStyle)
        Spacer(modifier = Modifier.height(80.dp))
        Box (modifier = Modifier.fillMaxWidth()) {
            Text("Email Address", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        // Email TextField
        BuildUiInputText()
        Spacer(Modifier.height(16.dp))
        // Password field
        Box (modifier = Modifier.fillMaxWidth()) {
            Text("Password", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText()
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text("Forgot password", style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onContinue(email) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
        ) {
            Text("Sign In", color = Color.White, style = LocalMyTypography.current.bodyBold)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text("Don't have an account?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Create Account",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onCreateAccount() }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun BuildUiInputText(
    data: () -> String = { "" },
    placeHolder : () -> String = {"Enter email address"},
    isPassword : () -> Boolean = {false},
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isHidden by remember { mutableStateOf(false) }
    val onEvent = remember {
        Modifier
            .size(30.dp)
            .clickable {
                isHidden = !isHidden
            }
    }
    TextField(
        value = data(),
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                placeHolder(),
                style = LocalMyTypography.current.bodyRegular,
                color = colorResource(R.color.gray_2)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if (isPassword()) {
                if (isHidden){
                    Icon(painter = painterResource(R.drawable.invisible),
                        "visibility", modifier = onEvent )
                }else {
                    Icon(painter = painterResource(R.drawable.visible), "visibility",
                        modifier = onEvent
                    )
                }
            }
        },
        visualTransformation = if (isPassword()) {
            if (isHidden) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None

        }
    )
}


@Preview
@Composable

fun PreviewSignInEmailScreen() {
    FitnessAppTheme {
        SignInEmailScreen(onContinue = {}, onCreateAccount = {}, onBack = {})
    }
}