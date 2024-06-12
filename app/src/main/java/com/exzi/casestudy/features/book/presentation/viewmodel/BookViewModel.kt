package com.exzi.casestudy.features.book.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exzi.casestudy.features.book.domain.entity.BookEntity
import com.exzi.casestudy.features.book.domain.use_case.BookUseCase
import com.exzi.casestudy.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookUseCase: BookUseCase,
) : ViewModel() {

    private val bookMutableLiveData = MutableLiveData<ResultData<BookEntity>>()

    val bookLiveData: LiveData<ResultData<BookEntity>>
        get() = bookMutableLiveData

    init {
        requestBook()
    }

     private fun requestBook() {
        viewModelScope.launch {
            bookUseCase.book(null)
                .flowOn(Dispatchers.IO)
                .onStart {
                    bookMutableLiveData.postValue(ResultData.Loading)
                }
                .catch { throwable ->
                    bookMutableLiveData.postValue(ResultData.Failure(error = throwable))
                }
                .collect { books ->
                    bookMutableLiveData.value = ResultData.Success(data = books)
                }
        }
    }
}