package com.marusys.fitnessapp.navigation

import android.app.ActionBar
import android.app.Activity
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.marusys.fitnessapp.R
import com.marusys.fitnessapp.feature.account.AccountViewModel
import com.marusys.fitnessapp.feature.home.HomeScreen
import com.marusys.fitnessapp.feature.profile.EditProfileScreen
import com.marusys.fitnessapp.feature.profile.HelpScreen
import com.marusys.fitnessapp.feature.profile.ProfileEvent
import com.marusys.fitnessapp.feature.profile.ProfileScreen
import com.marusys.fitnessapp.feature.profile.ProfileViewModel
import com.marusys.fitnessapp.feature.profile.SettingLanguageScreen
import com.marusys.fitnessapp.ui.theme.FitnessAppTheme
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import com.marusys.fitnessapp.ui.theme.MyTypograph
import org.koin.androidx.compose.koinViewModel
import java.util.Locale
import androidx.compose.ui.platform.LocalResources


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostRoute(modifier: Modifier = Modifier){
    val TAG = "HostRoute"
    val hostController = rememberNavController()
    var routePath by remember { mutableStateOf(HomeRoute) }
    val selectedIndex by remember {
        derivedStateOf {
            mainRouteList.indexOfFirst { it.route == routePath }
        }
    }
    val isShownTopBar by remember {
        derivedStateOf {
            Log.d(TAG, "HostRoute: ===> selectedIndex = $selectedIndex ")
            selectedIndex == 0
        }
    }

    val isShownBackTopBar by remember {
        derivedStateOf {
            val index =  mainRouteList.indexOfFirst { it.route == routePath }
            Log.d(TAG, "HostRoute: => index = $index - routePath = $routePath")
            index >= 3
        }
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = false // set true if you want dark icons (for light backgrounds)
        )
    }

    Scaffold(
        topBar = {
            if (!isShownTopBar) {
                TopAppBar(
                    title = {
                         mainRouteList[selectedIndex].content()
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    navigationIcon = {
                        if (isShownBackTopBar){
                            Row (modifier = Modifier.wrapContentWidth()){
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "back",
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable {
                                            val routeData =
                                                hostController.previousBackStackEntry?.destination?.route
                                                    ?: ""
                                            Log.d(TAG, "HostRoute: =====> routeData = $routeData")
                                            routePath = routeData
                                            hostController.popBackStack()
                                        }
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }

                        }
                    }
                )
            }
        },
        bottomBar = {
            if (!isShownBackTopBar) {
                NavigationBar{
                    mainRouteList.fastForEachIndexed { index , value ->
                        if (index < 3) {
                            key(value.route) {
                                NavigationBarItem(
                                    selected = selectedIndex == index,
                                    onClick = {
                                        routePath = value.route
                                        hostController.navigate(value.route)
                                    },
                                    icon = {
                                        if (index == selectedIndex) {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(value.selectedIcon),
                                                value.route
                                            )
                                        } else {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(value.icon),
                                                value.route
                                            )
                                        }
                                    },
                                    label = { value.content }
                                )
                            }
                        }
                    }
                }

            }
        }
    ) { padding ->
        NavHost(navController = hostController, startDestination = "home_graph",) {
            mainUi(padding, navController = hostController){
                routePath = it
            }
        }
    }

}

@Preview
@Composable
fun PreviewHostRoute(){
    FitnessAppTheme {
        CompositionLocalProvider(LocalMyTypography provides MyTypograph()) {
            HostRoute()
        }
    }
}

fun NavGraphBuilder.mainUi(
    paddingValues: PaddingValues,
    navController: NavController,
    onNavigate: (String) -> Unit,
) {
    val TAG = "mainUi"
    navigation(startDestination = "Home", route = "home_graph"){
        composable(HomeRoute){
            HomeScreen(paddingValues = paddingValues)
        }
        composable(ActivityRoute) {

        }
        composable(ProfileRoute) {
            Log.d(TAG, "mainUi: =====> profile")
            val viewModel = koinViewModel<ProfileViewModel> ()
            ProfileScreen(paddingValues = paddingValues,viewModel = viewModel) {
                when(it){
                    ProfileEvent.Logout -> {}
                    ProfileEvent.NavigateChangePassword -> {}
                    ProfileEvent.NavigateEditProfile -> {
                        onNavigate(EditRoute)
//                        onPosition(3)
                        navController.navigate(EditRoute)
                    }
                    ProfileEvent.NavigateHelp -> {
                        onNavigate(HelpRoute)
//                        onPosition(6)

                        navController.navigate(HelpRoute)
                    }
                    ProfileEvent.NavigateLanguage -> {
                        onNavigate(LanguageRoute)
//                        onPosition(4)
                        navController.navigate(LanguageRoute)
                    }
                    ProfileEvent.None -> {

                    }
                }
            }
        }
        composable(EditRoute) {
            val viewModel = koinViewModel<AccountViewModel> ()
            EditProfileScreen(viewModel = viewModel)
        }
        composable(HelpRoute) {
            HelpScreen {  }
        }
        composable(ChangePassRoute) {
            HelpScreen {  }
        }
        composable(LanguageRoute) {
            SettingLanguageScreen {
                navController.popBackStack()
            }
        }
    }
}

@Stable
data class Route (val route : String, val content: @Composable () -> Unit, val icon : Int, val selectedIcon : Int){

}
val mainRouteList = listOf(
    Route(HomeRoute,content = {
        Text(text = stringResource(R.string.home),
            color = Color.Black,
            style = LocalMyTypography.current.bodyBold)
    }, R.drawable.home_no_select, R.drawable.home_select),
    Route(ActivityRoute,  content = {
        Text(text = stringResource(R.string.activity),color = Color.Black, style = LocalMyTypography.current.bodyBold)
    }, R.drawable.activity, R.drawable.activity_select),
    Route(ProfileRoute,content = {
        Text(text = stringResource(R.string.my_profile),color = Color.Black, style = LocalMyTypography.current.bodyBold)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(EditRoute,content = {
        Text(text = stringResource(R.string.edit_profile),color = Color.Black, style = LocalMyTypography.current.bodyBold)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(LanguageRoute,content = {
        Text(text = stringResource(R.string.language),color = Color.Black, style = LocalMyTypography.current.bodyBold)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(ChangePassRoute,content = {
        Text(text = stringResource(R.string.change_password),color = Color.Black, style = LocalMyTypography.current.bodyBold)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(HelpRoute,content = {
        Text(text = stringResource(R.string.help), color = Color.Black,style = LocalMyTypography.current.bodyBold)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
)
private const val HomeRoute = "home"
private const val ActivityRoute = "activity"
private const val ProfileRoute = "profile"
private const val EditRoute = "edit_profile"
private const val LanguageRoute = "language"
private const val ChangePassRoute = "change_password"
private const val HelpRoute = "help"
