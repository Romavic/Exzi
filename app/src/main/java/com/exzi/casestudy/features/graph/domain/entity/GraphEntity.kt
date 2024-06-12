package com.exzi.casestudy.features.graph.domain.entity

class GraphEntity : ArrayList<GraphEntityItem>()

data class GraphEntityItem(
    var close: Long?,
    var closeF: String?,
    var high: Long?,
    var highF: String?,
    var low: Long?,
    var lowF: String?,
    var `open`: Long?,
    var openF: String?,
    var pair: String?,
    var pairId: Int?,
    var time: Int?,
    var volume: Long?,
    var volumeF: String?
)