package com.countriesinfo.ui.adapter


import com.countriesinfo.databinding.ItemCountryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countriesinfo.data.model.CountiesInfoResponseItem

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countries = ArrayList<CountiesInfoResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    fun setData(newData: List<CountiesInfoResponseItem>) {
        countries.clear()
        countries.addAll(newData)
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountiesInfoResponseItem) {
            binding.tvCountryName.text = country.name
            binding.tvCountryRegion.text = country.region
            binding.tvCountryCode.text = country.code
            binding.tvCountryCapital.text = country.capital
        }
    }
}