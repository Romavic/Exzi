package com.exzi.casestudy.features.book.di

import com.exzi.casestudy.features.book.data.data_source.BookDataSource
import com.exzi.casestudy.features.book.data.repository.BookRepositoryImpl
import com.exzi.casestudy.features.book.domain.mappers.BookMapper
import com.exzi.casestudy.features.book.domain.repository.BookRepository
import com.exzi.casestudy.features.book.domain.use_case.BookUseCase
import com.exzi.casestudy.features.book.presentation.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val bookModuleDi = module {

    single {
        get<Retrofit>().create(
            BookDataSource::class.java
        )
    }

    factory<BookRepository> {
        BookRepositoryImpl(
            bookDataSource = get(),
            bookMapper = BookMapper()
        )
    }

    factory {
        BookUseCase(
            graphRepository = get()
        )
    }

    viewModel {
        BookViewModel(
            bookUseCase = get(),
        )
    }
}