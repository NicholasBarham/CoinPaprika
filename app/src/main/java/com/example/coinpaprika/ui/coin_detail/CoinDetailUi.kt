package com.example.coinpaprika.ui.coin_detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coinpaprika.ui.navigation.Screen

fun NavGraphBuilder.coinDetailScreen(navigateToCoinList: () -> Unit) {
    composable(
        route = Screen.CoinDetail.route + "/{coinId}",
        arguments = listOf(
            navArgument("coinId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        val viewModel: CoinDetailViewModel = hiltViewModel<CoinDetailViewModelImpl>()
        CoinDetailUI()
    }
}

fun NavController.navigateToCoinDetail(coinId: String) {
    navigate(Screen.AllCoins.withArgs(coinId))
}

@Composable
fun CoinDetailUI() {

}