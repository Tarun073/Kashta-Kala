package com.kashta.kala.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kashta.kala.MainActivity
import com.kashta.kala.R
import com.kashta.kala.databinding.FragmentHomeBinding
import com.kashta.kala.utils.LanguageHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateLanguageButton()

        binding.cardCatalog.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_catalog)
        }

        binding.cardEstimator.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_estimator)
        }

        binding.cardQuotes.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_quotes)
        }

        binding.cardPortfolio.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_portfolio)
        }

        binding.btnLanguage.setOnClickListener {
            toggleLanguage()
        }
    }

    private fun updateLanguageButton() {
        val isKannada = LanguageHelper.isKannada(requireContext())
        binding.btnLanguage.text = if (isKannada) "English" else "ಕನ್ನಡ"
    }

    private fun toggleLanguage() {
        val isKannada = LanguageHelper.isKannada(requireContext())
        val newLang = if (isKannada) "en" else "kn"
        LanguageHelper.setLanguage(requireContext(), newLang)
        restartApp()
    }

    private fun restartApp() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}