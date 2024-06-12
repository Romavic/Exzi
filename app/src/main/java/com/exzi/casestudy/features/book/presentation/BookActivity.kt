package com.exzi.casestudy.features.book.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exzi.casestudy.R
import com.exzi.casestudy.databinding.BookActivityBinding
import com.exzi.casestudy.features.book.presentation.adapters.ProgressAdapter
import com.exzi.casestudy.features.book.presentation.viewmodel.BookViewModel
import com.exzi.casestudy.utils.ResultData
import com.exzi.casestudy.utils.extensions.gone
import com.exzi.casestudy.utils.extensions.visible
import org.koin.android.ext.android.inject

class BookActivity : AppCompatActivity() {

    private val viewModel: BookViewModel by inject()
    private lateinit var binding: BookActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BookActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requestBook()
    }

    private fun requestBook() {
        viewModel.bookLiveData.observe(this) { result ->
            when (result) {
                is ResultData.Success -> {
                    with(binding) {
                        progressLoad.gone()
                        with(recyclerView) {
                            visible()
                            layoutManager = LinearLayoutManager(this@BookActivity)
                            adapter = ProgressAdapter(result.data)
                        }
                    }
                }

                is ResultData.Failure -> {
                    with(binding) {
                        progressLoad.gone()
                        recyclerView.gone()
                    }
                    Toast.makeText(
                        this@BookActivity,
                        R.string.graph_activity_error_load_data,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultData.Loading -> {
                    with(binding) {
                        progressLoad.visible()
                        recyclerView.gone()
                    }
                }
            }
        }
    }
}
