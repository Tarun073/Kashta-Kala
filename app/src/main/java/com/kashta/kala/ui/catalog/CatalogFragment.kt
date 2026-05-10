package com.kashta.kala.ui.catalog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kashta.kala.KashtaApp
import com.kashta.kala.data.model.FavouriteDesign
import com.kashta.kala.data.model.FurnitureDesign
import com.kashta.kala.databinding.FragmentCatalogBinding
import com.kashta.kala.utils.DataSource
import kotlinx.coroutines.launch
import com.kashta.kala.utils.KeyboardHelper

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CatalogAdapter
    private var allDesigns = DataSource.getFurnitureList()
    private var selectedCategory = "All"
    private var searchQuery = ""

    private val repository by lazy {
        (requireActivity().application as KashtaApp).repository
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCategories()
        setupSearch()
        loadFavourites()
    }

    private fun setupRecyclerView() {
        adapter = CatalogAdapter(allDesigns) { design ->
            toggleFavourite(design)
        }
        binding.rvCatalog.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatalog.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString().trim()
                filterDesigns()
            }
        })
        binding.etSearch.setOnEditorActionListener { _, _, _ ->
            KeyboardHelper.hideKeyboard(requireActivity())
            true
        }
    }

    private fun setupCategories() {
        DataSource.categories.forEach { category ->
            val button = Button(requireContext()).apply {
                text = category
                textSize = 12f
                setPadding(40, 16, 40, 16)
                setBackgroundColor(
                    if (category == selectedCategory)
                        android.graphics.Color.parseColor("#3A7D5E")
                    else
                        android.graphics.Color.parseColor("#EAF0EC")
                )
                setTextColor(
                    if (category == selectedCategory)
                        android.graphics.Color.WHITE
                    else
                        android.graphics.Color.parseColor("#3A7D5E")
                )
                val params = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(6, 0, 6, 0) }
                layoutParams = params
                setOnClickListener { onCategorySelected(category) }
            }
            binding.layoutCategories.addView(button)
        }
    }

    private fun onCategorySelected(category: String) {
        selectedCategory = category
        binding.layoutCategories.removeAllViews()
        setupCategories()
        filterDesigns()
    }

    private fun filterDesigns() {
        var filtered = if (selectedCategory == "All") allDesigns
        else allDesigns.filter { it.category == selectedCategory }

        if (searchQuery.isNotEmpty()) {
            filtered = filtered.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.category.contains(searchQuery, ignoreCase = true) ||
                        it.suggestedWood.contains(searchQuery, ignoreCase = true)
            }
        }
        adapter.updateList(filtered)
    }

    private fun loadFavourites() {
        repository.allFavourites.observe(viewLifecycleOwner) { favourites ->
            val favIds = favourites.map { it.designId }
            allDesigns = allDesigns.map { design ->
                design.copy(isFavourite = design.id in favIds)
            }
            filterDesigns()
        }
    }

    private fun toggleFavourite(design: FurnitureDesign) {
        lifecycleScope.launch {
            if (repository.isFavourite(design.id)) {
                repository.deleteFavourite(
                    FavouriteDesign(design.id, design.name, design.category, design.imageRes)
                )
            } else {
                repository.insertFavourite(
                    FavouriteDesign(design.id, design.name, design.category, design.imageRes)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}