package com.marusys.fitnessapp.feature.profile

import android.app.LocaleManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.google.firebase.database.core.Context
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun SettingLanguageScreen(
    onBackClick: () -> Unit = {},
) {
    var selectedLanguage by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(70.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                LanguageItem(
                    title = "Tiếng Việt",
                    flagRes = R.drawable.vietnam, // ảnh lá cờ Việt Nam
                    selected = selectedLanguage == "vi",
                    onClick = { selectedLanguage = "vi" }
                )
                HorizontalDivider()
                LanguageItem(
                    title = "English",
                    flagRes = R.drawable.united_kingdom, // ảnh lá cờ UK/US
                    selected = selectedLanguage == "en",
                    onClick = { selectedLanguage = "en" }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút lưu
        Button(
            onClick = {
                changeLocales(context, selectedLanguage)
                onBackClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B51E0))
        ) {
            Text(stringResource(R.string.save), color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun LanguageItem(title: String, flagRes: Int, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = flagRes),
            contentDescription = title,
            modifier = Modifier.size(32.dp),
            tint = Color.Unspecified // để hiển thị cờ đúng màu
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = selected,
            onClick = { onClick() }
        )
    }
}

fun changeLocales(context: android.content.Context, localeString : String){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = android.os.LocaleList.forLanguageTags(localeString)
    }else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(localeString))
    }
}

@Preview
@Composable
fun PreviewSettingLanguageScreen() {
    FitnessAppTheme {
        SettingLanguageScreen {  }
    }
}
