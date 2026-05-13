package com.kashta.kala.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kashta.kala.R
import com.kashta.kala.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private var isLoginMode = true
    private var isEmailMode = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        updateMode()
    }

    private fun setupListeners() {
        binding.toggleAuthType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                isEmailMode = checkedId == R.id.btn_mode_email
                updateInputType()
            }
        }

        binding.tvSwitchMode.setOnClickListener {
            isLoginMode = !isLoginMode
            updateMode()
        }

        binding.btnAction.setOnClickListener {
            handleAuth()
        }
    }

    private fun updateMode() {
        if (isLoginMode) {
            binding.tvTitle.setText(R.string.title_login)
            binding.btnAction.setText(R.string.btn_login)
            binding.tvSwitchMode.setText(R.string.msg_no_account)
            binding.tilName.isVisible = false
        } else {
            binding.tvTitle.setText(R.string.title_register)
            binding.btnAction.setText(R.string.btn_register)
            binding.tvSwitchMode.setText(R.string.msg_have_account)
            binding.tilName.isVisible = true
        }
    }

    private fun updateInputType() {
        if (isEmailMode) {
            binding.tilIdentifier.hint = getString(R.string.hint_email)
            binding.etIdentifier.inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        } else {
            binding.tilIdentifier.hint = getString(R.string.hint_phone)
            binding.etIdentifier.inputType = android.text.InputType.TYPE_CLASS_PHONE
        }
        binding.etIdentifier.text?.clear()
    }

    private fun handleAuth() {
        val identifier = binding.etIdentifier.text.toString()
        val password = binding.etPassword.text.toString()
        val name = binding.etName.text.toString()

        if (identifier.isBlank() || password.isBlank() || (!isLoginMode && name.isBlank())) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Mock Authentication
        Toast.makeText(requireContext(), if (isLoginMode) "Logged in!" else "Registered!", Toast.LENGTH_SHORT).show()
        
        // Navigate to Home
        findNavController().navigate(R.id.action_auth_to_home)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}