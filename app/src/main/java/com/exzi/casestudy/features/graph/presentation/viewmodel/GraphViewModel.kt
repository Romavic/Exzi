package com.exzi.casestudy.features.graph.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exzi.casestudy.features.graph.domain.use_case.GraphUseCase
import com.exzi.casestudy.utils.ResultData
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GraphViewModel(
    private val graphUseCase: GraphUseCase,
) : ViewModel() {

    private val candleMutableLiveData =
        MutableLiveData<ResultData<List<CandleEntry>>>()

    val candleLiveData: LiveData<ResultData<List<CandleEntry>>>
        get() = candleMutableLiveData

    init {
        requestGraph()
    }

     fun requestGraph() {
        viewModelScope.launch {
            graphUseCase.graph(null)
                .flowOn(Dispatchers.IO)
                .onStart {
                    candleMutableLiveData.postValue(ResultData.Loading)
                }
                .catch { throwable ->
                    candleMutableLiveData.postValue(ResultData.Failure(error = throwable))
                }
                .collect { graphEntityItems ->
                    candleMutableLiveData.value = ResultData.Success(
                        data = graphEntityItems.mapIndexed { index, item ->
                            CandleEntry(
                                index.toFloat(),
                                item.high?.toFloat() ?: 0f,
                                item.low?.toFloat() ?: 0f,
                                item.open?.toFloat() ?: 0f,
                                item.close?.toFloat() ?: 0f
                            )
                        }
                    )
                }
        }
    }
}