package com.example.coinpaprika.ui.all_coins

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.coinpaprika.R
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinType
import com.example.coinpaprika.ui.navigation.Screen

fun NavGraphBuilder.allCoinsScreen(navigateToCoinDetail: (coinId: String) -> Unit) {
    composable(
        route = Screen.AllCoins.route
    ) {
        val viewModel: AllCoinsViewModel = hiltViewModel<AllCoinsViewModelImpl>()
        val coinList = viewModel.coins.collectAsState()

        LaunchedEffect(key1 = true) {
            viewModel.refreshCoinList()
        }

        AllCoinsUI(
            coinClicked = { coinId -> navigateToCoinDetail(coinId) },
            coinList = coinList.value
        )
    }
}

fun NavController.navigateToAllCoins() {
    navigate(Screen.AllCoins.route)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllCoinsUI(
    coinList: List<Coin>,
    coinClicked: (coinId: String) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Coin Paprika",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(coinList) { index, coin ->
                CoinItem(
                    onCoinClick = coinClicked,
                    coin = coin,
                    modifier = Modifier
                        .animateItemPlacement()
                        .fillMaxWidth()
                )

                if (index < coinList.lastIndex)
                    Divider(color = Color.Black.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun CoinItem(
    onCoinClick: (coinId: String) -> Unit,
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onCoinClick(coin.id) }
            .padding(16.dp)
    ) {
        Text(
            text = coin.name.trim(),
            fontSize = 24.sp,
            modifier = Modifier.alignByBaseline()
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "(${coin.symbol})",
            fontSize = 12.sp,
            modifier = Modifier.alignByBaseline()
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Right Arrow",
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AllCoinsUIPreview() {
    AllCoinsUI( createCoinList(50), {})
}

@Preview(showBackground = true)
@Composable
private fun CoinItemPreview() {
    CoinItem(
        onCoinClick = {},
        coin = Coin("1", "Bitcoin", "BTC", 0, true, true, CoinType.COIN),
        modifier = Modifier.fillMaxWidth()
    )
}

private fun createCoinList(size: Int): List<Coin> {
    return List(size) { index -> createCoin(index) }
}

private fun createCoin(index: Int): Coin {
    return Coin(
        id = index.toString(),
        name = "Coin $index",
        symbol = "BTC$index",
        rank = index,
        isNew = true,
        isActive = true,
        coinType = CoinType.COIN
    )
}
