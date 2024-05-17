package com.example.coinpaprika.ui.all_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinpaprika.data.api.CoinManager
import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

interface AllCoinsViewModel {
    val coins: StateFlow<List<Coin>>
    val isLoading: StateFlow<Boolean>
    val error: StateFlow<ApiError?>

    fun refreshCoinList()
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AllCoinsViewModelImpl @Inject constructor(
    private val coinManager: CoinManager
) : ViewModel(), AllCoinsViewModel {

    private val refreshClicked: MutableSharedFlow<Unit> = MutableSharedFlow()

    private val receivingCoinsResults = refreshClicked
        .flatMapLatest { coinManager.getAllCoins() }
        .shareIn(viewModelScope, SharingStarted.Lazily)

    override val coins: StateFlow<List<Coin>> = receivingCoinsResults
        .filter { it is Result.Success }
        .map { (it as Result.Success).data }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    override val isLoading: StateFlow<Boolean> = merge(
        refreshClicked.map { true },
        receivingCoinsResults.map { false }
    ).stateIn(viewModelScope, SharingStarted.Lazily, false)

    override val error: StateFlow<ApiError?> = receivingCoinsResults
        .filter { it is Result.Error }
        .map { (it as Result.Error).error }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    override fun refreshCoinList() {
        viewModelScope.launch {
            refreshClicked.emit(Unit)
        }
    }
}