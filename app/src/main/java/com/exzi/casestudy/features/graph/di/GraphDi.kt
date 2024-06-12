package com.exzi.casestudy.features.graph.di

import com.exzi.casestudy.features.graph.data.data_source.GraphDataSource
import com.exzi.casestudy.features.graph.data.repository.GraphRepositoryImpl
import com.exzi.casestudy.features.graph.domain.mappers.GraphMapper
import com.exzi.casestudy.features.graph.domain.repository.GraphRepository
import com.exzi.casestudy.features.graph.domain.use_case.GraphUseCase
import com.exzi.casestudy.features.graph.presentation.viewmodel.GraphViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val graphModuleDi = module {

    single {
        get<Retrofit>().create(
            GraphDataSource::class.java
        )
    }

    factory<GraphRepository> {
        GraphRepositoryImpl(
            graphDataSource = get(),
            graphMapper =  GraphMapper()
        )
    }

    factory {
        GraphUseCase(
            graphRepository = get()
        )
    }

    viewModel {
        GraphViewModel(
            graphUseCase = get(),
        )
    }
}