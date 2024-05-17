package com.example.coinpaprika.ui.coin_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.coinpaprika.R
import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.CoinType
import com.example.coinpaprika.data.api.api_data.Links
import com.example.coinpaprika.data.api.api_data.LinksExtended
import com.example.coinpaprika.data.api.api_data.Stats
import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.Whitepaper
import com.example.coinpaprika.ui.all_coins.ApiErrorUI
import com.example.coinpaprika.ui.navigation.Screen
import java.time.OffsetDateTime

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


        val coinId = it.arguments?.getString("coinId") ?: ""
        println("CoinDetailScreen: coinId = $coinId")
        val coin = viewModel.coinDetail.collectAsState()
        val error = viewModel.error.collectAsState()
        val isLoading = viewModel.isLoading.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getCoinDetail(coinId)
        }

        CoinDetailUI(
            coinDetail = coin.value,
            apiError = error.value,
            isLoading = isLoading.value
        )
    }
}

fun NavController.navigateToCoinDetail(coinId: String) {
    navigate(Screen.CoinDetail.withArgs(coinId))
}

@Composable
fun CoinDetailUI(
    coinDetail: CoinDetail?,
    apiError: ApiError?,
    isLoading: Boolean
) {
    Column(
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
                text = stringResource(R.string.coin_paprika),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (apiError != null) {
                Text(
                    text = ApiErrorUI(apiError),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }

            if(coinDetail != null) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SubcomposeAsyncImage(
                        model = coinDetail.logo,
                        contentDescription = null,
                        loading = { CircularProgressIndicator() },
                        modifier = Modifier
                            .fillMaxHeight(0.1f)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${coinDetail.name} (${coinDetail.rank})",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = coinDetail.symbol,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = coinDetail.description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AllCoinsUIPreview() {
    CoinDetailUI(
        coinDetail = createCoinDetail(),
        apiError = ApiError.UNKNOWN,
        isLoading = true
    )
}

private fun createCoinDetail(): CoinDetail {
    return CoinDetail(
        id = "coin-id",
        name = "Coin Name",
        symbol = "COIN_NAME",
        rank = 1,
        isNew = true,
        isActive = true,
        type = CoinType.COIN,
        logo = "https://static.coinpaprika.com/coin/eth-ethereum/logo.png",
        tags = listOf(Tag("tag-id", "tag-name", 0, 0)),
        team = listOf(Team("team-id", "team_name", "team_position")),
        description = "Coin Description",
        message = "Coin Message",
        openSource = true,
        startedAt = OffsetDateTime.now(),
        developmentStatus = "development-status",
        hardwareWallet = false,
        proofType = "proof-type",
        orgStructure = "org-structure",
        hashAlgorithm = "hash-algorithm",
        links = Links(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
        linksExtended = listOf(LinksExtended("url", "type", Stats(0, 0, 0, 0))),
        whitepaper = Whitepaper("link", "thumbnail"),
        firstDataAt = OffsetDateTime.now(),
        lastDataAt = OffsetDateTime.now()
    )
}