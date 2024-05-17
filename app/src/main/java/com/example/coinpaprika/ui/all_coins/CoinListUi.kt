package com.example.coinpaprika.ui.all_coins

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.coinpaprika.ui.navigation.Screen

fun NavGraphBuilder.coinListScreen(navigateToCoinDetail: (coinId: String) -> Unit) {
    composable(
        route = Screen.CoinList.route
    ) {
        val viewModel: AllCoinsViewModel = hiltViewModel<AllCoinsViewModelImpl>()
        CoinListUI()
    }
}

fun NavController.navigateToCoinList() {
    navigate(Screen.CoinList.route)
}

@Composable
fun CoinListUI() {

}
