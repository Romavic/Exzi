package com.exzi.casestudy.features.book.domain.mappers

import com.exzi.casestudy.features.book.data.dto.response.BookResponse
import com.exzi.casestudy.features.book.data.dto.response.OrderResponse
import com.exzi.casestudy.features.book.domain.entity.BookEntity
import com.exzi.casestudy.features.book.domain.entity.OrderEntity
import com.exzi.casestudy.utils.Mapper

class BookMapper : Mapper<BookResponse, BookEntity> {
    override fun map(data: BookResponse): BookEntity {
        return BookEntity(
            buyOrder = data.buyOrder.map { orderBuy ->
                OrderMapper().map(orderBuy)
            },
            sellOrder = data.sellOrder.map { orderSell ->
                OrderMapper().map(orderSell)
            }
        )
    }
}

class OrderMapper : Mapper<OrderResponse, OrderEntity> {
    override fun map(data: OrderResponse): OrderEntity {
        return OrderEntity(
            volume = data.volume,
            count = data.count,
            rate = data.rate,
            price = data.price,
            priceF = data.priceF,
            rateF = data.rateF,
            volumeF = data.volumeF,
        )
    }
}