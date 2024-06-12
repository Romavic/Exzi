package com.exzi.casestudy.features.book.data.dto.response

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("buy")
    val buyOrder: List<OrderResponse>,
    @SerializedName("sell")
    val sellOrder: List<OrderResponse>
)

data class OrderResponse(
    @SerializedName("volume")
    val volume: Long?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Long?,
    @SerializedName("price")
    val price: Long?,
    @SerializedName("price_f")
    val priceF: String?,
    @SerializedName("rate_f")
    val rateF: String?,
    @SerializedName("volume_f")
    val volumeF: String?
)
