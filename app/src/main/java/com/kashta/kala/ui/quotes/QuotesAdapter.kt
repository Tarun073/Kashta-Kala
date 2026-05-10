package com.kashta.kala.ui.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kashta.kala.data.model.Quote
import com.kashta.kala.databinding.ItemQuoteBinding
import com.kashta.kala.utils.FormatHelper

class QuotesAdapter(
    private var quotes: List<Quote>,
    private val onDeleteClick: (Quote) -> Unit,
    private val onShareClick: (Quote) -> Unit
) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        with(holder.binding) {
            tvItemName.text = quote.itemName
            tvDate.text = quote.date
            tvWoodType.text = quote.woodType
            tvDimensions.text = "${quote.length}×${quote.width}×${quote.height} ft"
            tvTotal.text = FormatHelper.formatCurrency(quote.totalCost)

            btnDelete.setOnClickListener { onDeleteClick(quote) }
            btnShare.setOnClickListener { onShareClick(quote) }
        }
    }

    override fun getItemCount() = quotes.size

    fun updateList(newList: List<Quote>) {
        quotes = newList
        notifyDataSetChanged()
    }
}