package com.kashta.kala.ui.estimator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kashta.kala.KashtaApp
import com.kashta.kala.data.model.Quote
import com.kashta.kala.databinding.FragmentEstimatorBinding
import com.kashta.kala.utils.KeyboardHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import com.kashta.kala.utils.FormatHelper

class EstimatorFragment : Fragment() {

    private var _binding: FragmentEstimatorBinding? = null
    private val binding get() = _binding!!

    private var selectedWood = "Teak"
    private val woodPrices = mapOf(
        "Teak" to 500,
        "Sal" to 350,
        "Plywood" to 200,
        "MDF" to 150,
        "Pine" to 250
    )

    private val repository by lazy {
        (requireActivity().application as KashtaApp).repository
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEstimatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWoodButtons()
        binding.btnCalculate.setOnClickListener {
            KeyboardHelper.hideKeyboard(requireActivity())
            calculate()
        }
    }

    private fun setupWoodButtons() {
        woodPrices.keys.forEach { wood ->
            val button = Button(requireContext()).apply {
                text = wood
                textSize = 12f
                setPadding(32, 16, 32, 16)
                updateButtonStyle(this, wood)
                val params = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(8, 0, 8, 0) }
                layoutParams = params
                setOnClickListener {
                    selectedWood = wood
                    refreshWoodButtons()
                }
            }
            binding.layoutWoodTypes.addView(button)
        }
    }

    private fun refreshWoodButtons() {
        for (i in 0 until binding.layoutWoodTypes.childCount) {
            val btn = binding.layoutWoodTypes.getChildAt(i) as Button
            updateButtonStyle(btn, btn.text.toString())
        }
    }

    private fun updateButtonStyle(button: Button, wood: String) {
        if (wood == selectedWood) {
            button.setBackgroundColor(android.graphics.Color.parseColor("#3A7D5E"))
            button.setTextColor(android.graphics.Color.WHITE)
        } else {
            button.setBackgroundColor(android.graphics.Color.parseColor("#EAF0EC"))
            button.setTextColor(android.graphics.Color.parseColor("#3A7D5E"))
        }
    }

    private fun calculate() {
        val lengthStr = binding.etLength.text.toString()
        val widthStr = binding.etWidth.text.toString()
        val heightStr = binding.etHeight.text.toString()

        if (lengthStr.isEmpty() || widthStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(requireContext(),
                "Please enter all dimensions", Toast.LENGTH_SHORT).show()
            return
        }

        val length = lengthStr.toDouble()
        val width = widthStr.toDouble()
        val height = heightStr.toDouble()

        val area = length * width
        val volume = length * width * height
        val wasteVolume = volume * 1.10
        val pricePerCuft = woodPrices[selectedWood] ?: 200
        val materialCost = wasteVolume * pricePerCuft
        val laborCost = binding.etLabor.text.toString().toDoubleOrNull() ?: 0.0
        val extraCost = binding.etExtra.text.toString().toDoubleOrNull() ?: 0.0
        val totalCost = materialCost + laborCost + extraCost

        binding.tvArea.text = "Area (L×W): ${"%.2f".format(area)} sq.ft"
        binding.tvVolume.text = "Volume (L×W×H): ${"%.2f".format(volume)} cu.ft"
        binding.tvWaste.text = "With 10% waste: ${"%.2f".format(wasteVolume)} cu.ft"
        binding.tvMaterialCost.text = "Material Cost ($selectedWood): ${FormatHelper.formatCurrency(materialCost)}"
        binding.tvTotal.text = "Total: ${FormatHelper.formatCurrency(totalCost)}"
        binding.cardResult.visibility = View.VISIBLE

        binding.btnSaveQuote.setOnClickListener {
            saveQuote(length, width, height, area, volume,
                wasteVolume, materialCost, laborCost, extraCost, totalCost)
        }
    }

    private fun saveQuote(
        length: Double, width: Double, height: Double,
        area: Double, volume: Double, wasteVolume: Double,
        materialCost: Double, laborCost: Double,
        extraCost: Double, totalCost: Double
    ) {
        val itemName = binding.etItemName.text.toString()
            .ifEmpty { "Unnamed Item" }
        val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            .format(Date())

        val quote = Quote(
            itemName = itemName,
            woodType = selectedWood,
            length = length,
            width = width,
            height = height,
            areasqft = area,
            volumeCuft = wasteVolume,
            materialCost = materialCost,
            laborCost = laborCost,
            extraCost = extraCost,
            totalCost = totalCost,
            date = date
        )

        lifecycleScope.launch {
            repository.insertQuote(quote)
            Toast.makeText(requireContext(),
                "Quote saved successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}