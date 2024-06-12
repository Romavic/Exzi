package com.exzi.casestudy.features.book.data.repository

import com.exzi.casestudy.features.book.data.data_source.BookDataSource
import com.exzi.casestudy.features.book.data.dto.resquest.BookRequest
import com.exzi.casestudy.features.book.domain.entity.BookEntity
import com.exzi.casestudy.features.book.domain.mappers.BookMapper
import com.exzi.casestudy.features.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepositoryImpl(
    private val bookDataSource: BookDataSource,
    private val bookMapper: BookMapper,
) : BookRepository {

    override suspend fun getBook(bookRequest: BookRequest?): Flow<BookEntity> = flow {
        return@flow emit(
            bookDataSource.getBookEndpoint(
                pairId = bookRequest?.pairId ?: 1041,
                buy = bookRequest?.buy ?: 1,
                sell = bookRequest?.sell ?: 1,
            ).let { graphResponseItems ->
                bookMapper.map(graphResponseItems)
            }
        )
    }
}
