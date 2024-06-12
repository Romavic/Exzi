package com.exzi.casestudy.features.book.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exzi.casestudy.R
import com.exzi.casestudy.features.book.domain.entity.BookEntity

class ProgressAdapter(
    private val bookEntity: BookEntity
) : RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buyProgressBar: ProgressBar = itemView.findViewById(R.id.buyProgressBar)
        val buyProgressText: TextView = itemView.findViewById(R.id.buyProgressText)
        val sellProgressBar: ProgressBar = itemView.findViewById(R.id.sellProgressBar)
        val sellProgressText: TextView = itemView.findViewById(R.id.sellProgressText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_chart_component, parent, false)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val buyOrder = bookEntity.buyOrder.getOrNull(position)
        val sellOrder = bookEntity.sellOrder.getOrNull(position)

        val buyVolume = buyOrder?.rate ?: 0L
        val sellVolume = sellOrder?.rate ?: 0L

        val minValueBuy = bookEntity.buyOrder.mapNotNull { it?.rate }.minOrNull() ?: 0L
        val maxValueBuy = bookEntity.buyOrder.mapNotNull { it?.rate }.maxOrNull() ?: 1L
        val minValueSell = bookEntity.sellOrder.mapNotNull { it?.rate }.minOrNull() ?: 0L
        val maxValueSell = bookEntity.sellOrder.mapNotNull { it?.rate }.maxOrNull() ?: 1L

        val buyProgress = ((buyVolume - minValueBuy).toFloat() / (maxValueBuy - minValueBuy) * 100).toInt()
        val sellProgress = ((sellVolume - minValueSell).toFloat() / (maxValueSell - minValueSell) * 100).toInt()

        holder.buyProgressBar.progress = buyProgress
        holder.buyProgressText.text = "${buyOrder?.priceF ?: "N/A"}"

        holder.sellProgressBar.progress = sellProgress
        holder.sellProgressText.text = "${sellOrder?.priceF ?: "N/A"}"
    }

    override fun getItemCount(): Int = maxOf(bookEntity.buyOrder.size, bookEntity.sellOrder.size)
}