package com.example.coinpaprika.ui.navigation

sealed class Screen(val route: String) {
    data object AllCoins : Screen("all_coins")
    data object CoinDetail : Screen("coin_detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}