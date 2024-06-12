package com.exzi.casestudy.features.graph.data.data_source

import com.exzi.casestudy.features.graph.data.dto.response.GraphResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface GraphDataSource {

    @GET("graph/hist")
    suspend fun getGraphEndpoint(
        @Query("t") t: String,
        @Query("r") r: String,
        @Query("limit") limit: Int,
        @Query("end") end: Long,
    ): List<GraphResponseItem>
}