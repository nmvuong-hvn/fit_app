package com.marusys.fitnessapp.ui.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.feature.profile.changeLocales

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val H1Style = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 32.sp,
    lineHeight = 21.sp
)

val H2ExtraBoldStyle = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_extrabold))),
    fontSize = 24.sp,
    lineHeight = 30.sp
)

val H2SemiboldStyle = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_semibold))),
    fontSize = 24.sp,
    lineHeight = 21.sp
)
val H2BoldStyle = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 24.sp,
    lineHeight = 21.sp
)

val H3SemiBoldStyle = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_semibold))),
    fontSize = 20.sp,
    lineHeight = 21.sp
)
val H3SBoldStyle = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 20.sp,
    lineHeight = 21.sp
)
val BodyRegular = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_regular))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val BodyMedium= TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_medium))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val BodySemi = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_semibold))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val BodyBold = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)


val Body2Regular = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_regular))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val Body2Medium= TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_medium))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val Body2Semi = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_semibold))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val Body2Bold = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val CapRegular = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_regular))),
    fontSize = 12.sp,
    lineHeight = 22.sp
)

val CapSemi = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_semibold))),
    fontSize = 12.sp,
    lineHeight = 22.sp
)
val CapBold = TextStyle(
    fontFamily = FontFamily(listOf(Font(R.font.overpass_bold))),
    fontSize = 12.sp,
    lineHeight = 22.sp
)


data class MyTypograph(
    val h1Style: TextStyle = H1Style,

    val h2ExtraBoldStyle: TextStyle = H2ExtraBoldStyle,
    val h2SemiboldStyle: TextStyle = H2SemiboldStyle,
    val h2BoldStyle: TextStyle = H2BoldStyle,

    val h3SemiboldStyle: TextStyle = H3SemiBoldStyle,
    val h3BoldStyle: TextStyle = H3SBoldStyle,

    val bodyRegular: TextStyle = BodyRegular,
    val bodyMedium: TextStyle = BodyMedium,
    val bodySemi: TextStyle = BodySemi,
    val bodyBold: TextStyle = BodyBold,

    val body2Regular: TextStyle = Body2Regular,
    val body2Medium: TextStyle = Body2Medium,
    val body2Semi: TextStyle = Body2Semi,
    val body2Bold: TextStyle = Body2Bold,

    val capRegular : TextStyle = CapRegular,
    val capSemi : TextStyle = CapSemi,
    val capBold: TextStyle = CapBold,
)

val LocalMyTypography = staticCompositionLocalOf { MyTypograph() }

@Composable
fun FitnessAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}