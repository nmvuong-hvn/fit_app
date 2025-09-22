package com.marusys.fitnessapp.feature.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    onContinue: (String , String) -> Unit,
    onCreateAccount: () -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp).verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(R.string.sign_in), style = LocalMyTypography.current.h2BoldStyle)
        Spacer(modifier = Modifier.height(80.dp))
        Box (modifier = Modifier.fillMaxWidth()) {
            Text( text = stringResource(R.string.email_address), style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        // Email TextField
        BuildUiInputText(
            placeHolder = { stringResource(R.string.email_address) },
            data = { email },
            onValueChange = { email = it }
        )
        Spacer(Modifier.height(16.dp))
        // Password field
        Box (modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.pass), style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        BuildUiInputText(
            placeHolder = {stringResource(R.string.pass)},
            data = {pass},
            onValueChange = {pass = it}
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(stringResource(R.string.forgot_pass), style = LocalMyTypography.current.bodyMedium)
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onContinue(email, pass) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
        ) {
            Text(stringResource(R.string.sign_in), color = Color.White, style = LocalMyTypography.current.bodyBold)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.not_account), style = LocalMyTypography.current.bodyRegular,)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                stringResource(R.string.create_account),
                color = Color.Black,
                style = LocalMyTypography.current.bodyBold,
                modifier = Modifier.clickable { onCreateAccount() }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun BuildUiInputText(
    data: () -> String = { "" },
    placeHolder : @Composable () -> String = {"Enter email address"},
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
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = colorResource(R.color.bg_item_category),
            unfocusedContainerColor = colorResource(R.color.bg_item_category)
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
//        SignInEmailScreen(onContinue = {}, onCreateAccount = {}, onBack = {})
    }
}