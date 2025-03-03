package com.countriesinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.countriesinfo.data.model.CountiesInfoResponseItem
import com.countriesinfo.data.network.NetworkStatus
import com.countriesinfo.databinding.ActivityMainBinding
import com.countriesinfo.ui.adapter.CountryAdapter
import com.countriesinfo.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  viewModel: CountriesViewModel
    private val countryAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvCountries.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.countriesState.collectLatest { state ->
                when (state) {
                    is NetworkStatus.Error -> {
                        withContext(Dispatchers.Main) {
                            showError(viewState = state)
                        }
                    }

                    NetworkStatus.Loading -> {
                        withContext(Dispatchers.Main) {
                            showLoading()
                        }                    }
                    is NetworkStatus.Success -> {
                        withContext(Dispatchers.Main){
                    showCountries(state.data)
                }
                    }
                }
            }
        }
    }

     fun showLoading() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            rvCountries.visibility = View.GONE
            tvError.visibility = View.GONE
        }
    }

     fun showCountries(data: List<CountiesInfoResponseItem>) {
        binding.apply {
            progressBar.visibility = View.GONE
            rvCountries.visibility = View.VISIBLE
            tvError.visibility = View.GONE
        }
        countryAdapter.setData(data)
    }

     fun showError(viewState: NetworkStatus.Error) {
        binding.apply {
            progressBar.visibility = View.GONE
            rvCountries.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvError.text = viewState.message
        }
    }
}