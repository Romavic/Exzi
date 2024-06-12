package com.exzi.casestudy.features.graph.domain.repository

import com.exzi.casestudy.features.graph.data.dto.resquest.GraphRequest
import com.exzi.casestudy.features.graph.domain.entity.GraphEntityItem
import kotlinx.coroutines.flow.Flow

interface GraphRepository {
    suspend fun getGraph(
        graphRequest: GraphRequest?
    ): Flow<List<GraphEntityItem>>
}