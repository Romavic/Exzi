package com.exzi.casestudy.features.graph.data.dto.response

import com.google.gson.annotations.SerializedName

data class GraphResponseItem(
    @SerializedName("low")
    val low: Long?,
    @SerializedName("high")
    val high: Long?,
    @SerializedName("volume")
    val volume: Long?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("open")
    val `open`: Long?,
    @SerializedName("close")
    val close: Long?,
    @SerializedName("pair_id")
    val pairId: Int?,
    @SerializedName("pair")
    val pair: String?,
    @SerializedName("low_f")
    val lowF: String?,
    @SerializedName("high_f")
    val highF: String?,
    @SerializedName("open_f")
    val openF: String?,
    @SerializedName("close_f")
    val closeF: String?,
    @SerializedName("volume_f")
    val volumeF: String?
)