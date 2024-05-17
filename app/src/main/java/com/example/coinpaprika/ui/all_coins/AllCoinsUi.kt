package com.example.coinpaprika.ui.all_coins

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.coinpaprika.ui.navigation.Screen

fun NavGraphBuilder.allCoinsScreen(navigateToCoinDetail: (coinId: String) -> Unit) {
    composable(
        route = Screen.AllCoins.route
    ) {
        val viewModel: AllCoinsViewModel = hiltViewModel<AllCoinsViewModelImpl>()
        AllCoinsUI()
    }
}

fun NavController.navigateToAllCoins() {
    navigate(Screen.AllCoins.route)
}

@Composable
fun AllCoinsUI() {

}
