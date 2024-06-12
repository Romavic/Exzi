package com.exzi.casestudy.features.book.domain.entity

data class BookEntity(
    val buyOrder: List<OrderEntity?>,
    val sellOrder: List<OrderEntity?>
)

data class OrderEntity(
    val volume: Long?,
    val count: Int?,
    val rate: Long?,
    val price: Long?,
    val priceF: String?,
    val rateF: String?,
    val volumeF: String?
)