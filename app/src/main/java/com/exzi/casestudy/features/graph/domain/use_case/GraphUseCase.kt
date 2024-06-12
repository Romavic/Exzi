package com.exzi.casestudy.features.graph.domain.use_case

import com.exzi.casestudy.features.graph.data.dto.resquest.GraphRequest
import com.exzi.casestudy.features.graph.domain.entity.GraphEntityItem
import com.exzi.casestudy.features.graph.domain.repository.GraphRepository
import kotlinx.coroutines.flow.Flow

class GraphUseCase(
    private val graphRepository: GraphRepository
) {
    suspend fun graph(graphRequest: GraphRequest?): Flow<List<GraphEntityItem>> {
        return graphRepository.getGraph(graphRequest)
    }
}