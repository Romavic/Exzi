package com.exzi.casestudy.features.graph.domain.mappers

import com.exzi.casestudy.features.graph.data.dto.response.GraphResponseItem
import com.exzi.casestudy.features.graph.domain.entity.GraphEntity
import com.exzi.casestudy.features.graph.domain.entity.GraphEntityItem
import com.exzi.casestudy.utils.Mapper

class GraphMapper : Mapper<List<GraphResponseItem>, GraphEntity> {
    override fun map(data: List<GraphResponseItem>): GraphEntity {
        return GraphEntity().apply {
            addAll(GraphItemMapper().map(data))
        }
    }
}

class GraphItemMapper : Mapper<List<GraphResponseItem>, List<GraphEntityItem>> {
    override fun map(data: List<GraphResponseItem>): List<GraphEntityItem> {
        return data.map { responseItem ->
            GraphEntityItem(
                close = responseItem.close,
                closeF = responseItem.closeF,
                high = responseItem.high,
                highF = responseItem.highF,
                low = responseItem.low,
                lowF = responseItem.lowF,
                open = responseItem.open,
                openF = responseItem.openF,
                pair = responseItem.pair,
                pairId = responseItem.pairId,
                time = responseItem.time,
                volume = responseItem.volume,
                volumeF = responseItem.volumeF
            )
        }
    }
}