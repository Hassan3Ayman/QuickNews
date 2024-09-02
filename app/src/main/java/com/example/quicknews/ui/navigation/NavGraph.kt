package com.smartapps.rscc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quicknews.ui.navigation.Screens
import com.example.quicknews.ui.navigation.ScreensArgs
import com.example.quicknews.ui.screens.home.HomeScreen
import com.example.quicknews.ui.screens.home.article_details.ArticleDetailsScreen

val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }

@Composable
fun NavGraph() {
    CompositionLocalProvider(LocalNavController provides rememberNavController()) {
        NavHost(
            navController = LocalNavController.current,
            startDestination = Screens.Home.route
        ) {
            composable(
                route = Screens.Home.route
            ) {
                HomeScreen()
            }

            articleDetailsRoute()
        }
    }

}

fun NavGraphBuilder.articleDetailsRoute() {
    composable(
        route = "${Screens.ArticleDetails.route}/{${ScreensArgs.ID}}",
        arguments = listOf(
            navArgument(ScreensArgs.ID) { NavType.StringType }
        )
    ) {
        ArticleDetailsScreen()
    }
}

fun NavController.navigateToArticleDetails(
    id: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = "${Screens.ArticleDetails.route}/${id}", builder = builder)
}