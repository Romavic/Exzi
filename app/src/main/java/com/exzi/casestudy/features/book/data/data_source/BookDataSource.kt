package com.exzi.casestudy.features.book.data.data_source

import com.exzi.casestudy.features.book.data.dto.response.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookDataSource {

    @GET("book/list")
    suspend fun getBookEndpoint(
        @Query("pair_id") pairId: Int,
        @Query("buy") buy: Int,
        @Query("sell") sell: Int,
    ): BookResponse
}