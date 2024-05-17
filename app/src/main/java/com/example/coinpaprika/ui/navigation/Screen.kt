package com.example.coinpaprika.ui.navigation

sealed class Screen(val route: String) {
    data object CoinList : Screen("coin_list")
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