package com.exzi.casestudy.features.book.domain.use_case

import com.exzi.casestudy.features.book.data.dto.resquest.BookRequest
import com.exzi.casestudy.features.book.domain.entity.BookEntity
import com.exzi.casestudy.features.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class BookUseCase(
    private val graphRepository: BookRepository
) {
    suspend fun book(graphRequest: BookRequest?): Flow<BookEntity> {
        return graphRepository.getBook(graphRequest)
    }
}