package com.kashta.kala.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kashta.kala.R
import com.kashta.kala.data.model.FurnitureDesign
import com.kashta.kala.databinding.ItemFurnitureBinding
import com.kashta.kala.utils.FormatHelper

class CatalogAdapter(
    private var designs: List<FurnitureDesign>,
    private val onFavouriteClick: (FurnitureDesign) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFurnitureBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFurnitureBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val design = designs[position]
        with(holder.binding) {
            tvDesignName.text = design.name
            tvCategory.text = design.category
            tvWoodType.text = design.suggestedWood
            tvPrice.text = FormatHelper.formatCurrency(design.estimatedPrice)
            ivFurniture.setImageResource(design.imageRes)

            btnFavourite.setImageResource(
                if (design.isFavourite) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )

            btnFavourite.setOnClickListener {
                onFavouriteClick(design)
            }
        }
    }

    override fun getItemCount() = designs.size

    fun updateList(newList: List<FurnitureDesign>) {
        designs = newList
        notifyDataSetChanged()
    }
}