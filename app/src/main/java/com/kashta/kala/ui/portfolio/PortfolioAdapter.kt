package com.kashta.kala.ui.portfolio

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kashta.kala.data.model.PortfolioItem
import com.kashta.kala.databinding.ItemPortfolioBinding

class PortfolioAdapter(
    private var items: List<PortfolioItem>,
    private val onDeleteClick: (PortfolioItem) -> Unit
) : RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPortfolioBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(Uri.parse(item.imageUri))
                .centerCrop()
                .into(ivPortfolio)

            tvCaption.text = item.caption.ifEmpty { "No caption" }
            btnDelete.setOnClickListener { onDeleteClick(item) }
        }
    }

    override fun getItemCount() = items.size

    fun updateList(newList: List<PortfolioItem>) {
        items = newList
        notifyDataSetChanged()
    }
}