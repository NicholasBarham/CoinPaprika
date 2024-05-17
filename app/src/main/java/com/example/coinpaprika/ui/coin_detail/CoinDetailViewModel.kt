package com.example.coinpaprika.ui.coin_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinpaprika.data.api.CoinManager
import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.CoinDetail
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

interface CoinDetailViewModel {
    val coinDetail: StateFlow<CoinDetail?>
    val isLoading: StateFlow<Boolean>
    val error: StateFlow<ApiError?>

    fun getCoinDetail(coinId: String)
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CoinDetailViewModelImpl @Inject constructor(
    private val coinManager: CoinManager
): ViewModel(), CoinDetailViewModel {

    private val fetchCoinDetailTrigger = MutableSharedFlow<String>()

    private val receivingCoinDetail = fetchCoinDetailTrigger
        .flatMapLatest { coinManager.getCoinById(it) }
        .shareIn(viewModelScope, SharingStarted.Lazily)

    override val coinDetail: StateFlow<CoinDetail?> = receivingCoinDetail
        .filter { it is Result.Success }
        .map { (it as Result.Success).data }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    override val isLoading: StateFlow<Boolean> = merge(
        fetchCoinDetailTrigger.map { true },
        receivingCoinDetail.map { false }
    ).stateIn(viewModelScope, SharingStarted.Lazily, false)

    override val error: StateFlow<ApiError?> = receivingCoinDetail
        .filter { it is Result.Error }
        .map { (it as Result.Error).error }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    override fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            fetchCoinDetailTrigger.emit(coinId)
        }
    }

}