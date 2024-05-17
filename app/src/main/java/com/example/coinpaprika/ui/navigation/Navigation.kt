package com.example.coinpaprika.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.coinpaprika.ui.all_coins.allCoinsScreen
import com.example.coinpaprika.ui.coin_detail.coinDetailScreen
import com.example.coinpaprika.ui.coin_detail.navigateToCoinDetail

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AllCoins.route) {
        allCoinsScreen(
            navigateToCoinDetail = { coinId -> navController.navigateToCoinDetail(coinId) }
        )
        coinDetailScreen(
            navigateToCoinList = { navController.popBackStack() }
        )
    }
}