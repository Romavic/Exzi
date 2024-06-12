package com.exzi.casestudy.features.graph.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.exzi.casestudy.R
import com.exzi.casestudy.databinding.GraphActivityBinding
import com.exzi.casestudy.features.book.presentation.BookActivity
import com.exzi.casestudy.features.graph.presentation.extensions.chartCandlesExtension
import com.exzi.casestudy.features.graph.presentation.viewmodel.GraphViewModel
import com.exzi.casestudy.utils.ResultData
import com.exzi.casestudy.utils.extensions.gone
import com.exzi.casestudy.utils.extensions.visible
import org.koin.android.ext.android.inject

class GraphActivity : AppCompatActivity() {

    private val viewModel: GraphViewModel by inject()
    private lateinit var binding: GraphActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GraphActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        requestGraph()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_plus -> {
                startActivity(
                    Intent(this, BookActivity::class.java)
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun requestGraph() {
        viewModel.candleLiveData.observe(this) { result ->
            when (result) {
                is ResultData.Success -> {
                    with(binding) {
                        chartCandles.apply {
                            visible()
                            chartCandlesExtension(result.data)
                        }
                        textState.gone()
                    }
                }

                is ResultData.Failure -> {
                    with(binding) {
                        chartCandles.gone()
                        textState.apply {
                            visible()
                            setText(R.string.graph_activity_error_load_data)
                            setOnClickListener {
                                viewModel.requestGraph()
                            }
                        }
                    }
                }

                is ResultData.Loading -> {
                    with(binding) {
                        chartCandles.gone()
                        textState.apply {
                            visible()
                            setText(R.string.graph_activity_load_data)
                        }
                    }
                }
            }
        }
    }
}