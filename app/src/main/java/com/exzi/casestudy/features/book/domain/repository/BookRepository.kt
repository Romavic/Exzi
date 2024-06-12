package com.exzi.casestudy.features.book.domain.repository

import com.exzi.casestudy.features.book.data.dto.resquest.BookRequest
import com.exzi.casestudy.features.book.domain.entity.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBook(
        bookRequest: BookRequest?
    ): Flow<BookEntity>
}
