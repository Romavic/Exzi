package com.exzi.casestudy.features.graph.data.repository

import com.exzi.casestudy.features.graph.data.data_source.GraphDataSource
import com.exzi.casestudy.features.graph.data.dto.resquest.GraphRequest
import com.exzi.casestudy.features.graph.domain.entity.GraphEntityItem
import com.exzi.casestudy.features.graph.domain.mappers.GraphMapper
import com.exzi.casestudy.features.graph.domain.repository.GraphRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GraphRepositoryImpl(
    private val graphDataSource: GraphDataSource,
    private val graphMapper: GraphMapper
) : GraphRepository {

    override suspend fun getGraph(
        graphRequest: GraphRequest?
    ): Flow<List<GraphEntityItem>> = flow {
        return@flow emit(
            graphDataSource.getGraphEndpoint(
                t = graphRequest?.t ?: "BTCUSDT",
                r = graphRequest?.r ?: "D",
                limit = graphRequest?.limit ?: 5000,
                end = graphRequest?.end ?: 1705363200
            ).let { graphResponseItems ->
                graphMapper.map(graphResponseItems).toList()
            }
        )
    }
}