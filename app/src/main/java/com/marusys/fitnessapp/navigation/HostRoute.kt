package com.marusys.fitnessapp.navigation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
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
import com.marusys.fitnessapp.ui.theme.LocalMyTypography
import org.koin.androidx.compose.koinViewModel
import com.marusys.fitnessapp.feature.account.AccountIntent
import com.marusys.fitnessapp.feature.account.SignInEmailScreen
import com.marusys.fitnessapp.feature.account.SignUpAccountScreen
import com.marusys.fitnessapp.feature.forgot_password.ForgotPasswordScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostRoute(modifier: Modifier = Modifier,  viewModel: AccountViewModel = koinViewModel<AccountViewModel>()){
    val TAG = "HostRoute"
    val hostController = rememberNavController()
    var routePath by remember { mutableStateOf(LoginRoute) }
    val selectedIndex by remember {
        derivedStateOf {
            mainRouteList.indexOfFirst { it.route == routePath }
        }
    }
    val isShownTopBar by remember {
        derivedStateOf {
            Log.d(TAG, "HostRoute: ===> selectedIndex = $selectedIndex ")
            selectedIndex in (1 .. 2)
        }
    }

    val isShownBottomNavigation by remember {
        derivedStateOf {
            val index =  mainRouteList.indexOfFirst { it.route == routePath }
            Log.d(TAG, "HostRoute: => index = $index - routePath = $routePath")
            index >= 3
        }
    }

    val isShownBackTopBar by remember {
        derivedStateOf {
            val index =  mainRouteList.indexOfFirst { it.route == routePath }
            Log.d(TAG, "HostRoute: => index = $index - routePath = $routePath")
            index in (1 .. 2)
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
            if (isShownTopBar) {
                TopAppBar(
                    modifier = Modifier.height(70.dp).fillMaxWidth(),
                    title = {
                        Box(modifier = Modifier.fillMaxWidth().padding(top = 40.dp), contentAlignment = Alignment.Center) {
                            mainRouteList[selectedIndex].content()
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    navigationIcon = {
                        Log.d(TAG, "HostRoute: ===> isShownBackTopBar = $isShownBottomNavigation ")
                        if (isShownBackTopBar){
                            Row (modifier = Modifier.wrapContentWidth().padding(top = 40.dp)){
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
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (isShownBottomNavigation) {
                NavigationBar{
                    mainRouteList.fastForEachIndexed { index , value ->
                        if (index in (3 .. 5)) {
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
        NavHost(navController = hostController, startDestination = "auth_graph") {
            auth(hostController, viewModel){
                routePath = it
            }
            mainUi(padding, navController = hostController){
                routePath = it
            }
        }
    }
}

@Preview
@Composable
fun PreviewHostRoute(){

}

fun NavGraphBuilder.onBoarding(navController: NavController){
    navigation("login", route = "auth_graph"){

    }
}

fun NavGraphBuilder.auth(navController: NavController,viewModel: AccountViewModel, onNavigate: (String) -> Unit){
    val TAG = "auth"
    navigation(LoginRoute, route = "auth_graph"){
        composable(LoginRoute){

            SignInEmailScreen(viewModel, onCreateAccount = {
                onNavigate(RegisterRoute)
                navController.navigate(RegisterRoute)
            }, onCreateForgot = {
                onNavigate(ForgotPassRoute)
                navController.navigate(ForgotPassRoute)
            }, onHomeScreen = {
                onNavigate(HomeRoute)
                navController.navigate("home_graph")
            })
        }
        composable(RegisterRoute){
            SignUpAccountScreen(viewModel = viewModel) {
                val route = navController.previousBackStackEntry?.destination?.route ?: ""
                Log.d(TAG, "auth: ====> route = $route")
                onNavigate(route)
                navController.popBackStack()
            }
        }
        composable(ForgotPassRoute){
            ForgotPasswordScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}

fun NavGraphBuilder.mainUi(
    paddingValues: PaddingValues,
    navController: NavController,
    onNavigate: (String) -> Unit,
) {
    val TAG = "mainUi"
    navigation(startDestination = HomeRoute, route = "home_graph"){
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
    Route(LoginRoute, content = {
        Text(text = stringResource(R.string.sign_in),
            color = Color.Black,
            style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.home_no_select, R.drawable.home_select),
    Route(RegisterRoute, content = {
        Text(text = stringResource(R.string.sign_up),
            color = Color.Black,
            style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.home_no_select, R.drawable.home_select),

    Route(ForgotPassRoute, content = {
        Text(text = stringResource(R.string.forgot_pass),
            color = Color.Black,
            style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.home_no_select, R.drawable.home_select),
    Route(HomeRoute,content = {
        Text(text = stringResource(R.string.home),
            color = Color.Black,
            style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.home_no_select, R.drawable.home_select),
    Route(ActivityRoute,  content = {
        Text(text = stringResource(R.string.activity),color = Color.Black, style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.activity, R.drawable.activity_select),
    Route(ProfileRoute,content = {
        Text(text = stringResource(R.string.my_profile),color = Color.Black, style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(EditRoute,content = {
        Text(text = stringResource(R.string.edit_profile),color = Color.Black, style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(LanguageRoute,content = {
        Text(text = stringResource(R.string.language),color = Color.Black, style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(ChangePassRoute,content = {
        Text(text = stringResource(R.string.change_password),color = Color.Black, style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
    Route(HelpRoute,content = {
        Text(text = stringResource(R.string.help), color = Color.Black,style = LocalMyTypography.current.h2BoldStyle)
    }, R.drawable.profile_no_select, R.drawable.profile_select),
)

private const val LoginRoute = "login"
private const val RegisterRoute = "register"
private const val ForgotPassRoute = "forgot_password"
private const val HomeRoute = "home"
private const val ActivityRoute = "activity"
private const val ProfileRoute = "profile"
private const val EditRoute = "edit_profile"
private const val LanguageRoute = "language"
private const val ChangePassRoute = "change_password"
private const val HelpRoute = "help"
