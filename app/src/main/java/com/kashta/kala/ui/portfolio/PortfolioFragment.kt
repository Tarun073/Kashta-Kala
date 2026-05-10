package com.kashta.kala.ui.portfolio

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kashta.kala.KashtaApp
import com.kashta.kala.data.model.PortfolioItem
import com.kashta.kala.databinding.FragmentPortfolioBinding
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PortfolioFragment : Fragment() {

    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PortfolioAdapter
    private var currentPhotoUri: Uri? = null

    companion object {
        private const val KEY_PHOTO_URI = "current_photo_uri"
    }

    private val repository by lazy {
        (requireActivity().application as KashtaApp).repository
    }

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data ?: return@registerForActivityResult
            requireActivity().contentResolver.takePersistableUriPermission(
                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            showCaptionDialog(uri.toString())
        }
    }

    private val takePicture = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoUri?.let { uri ->
                showCaptionDialog(uri.toString())
            }
        }
    }

    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) openCamera()
        else Toast.makeText(requireContext(),
            "Camera permission needed", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedInstanceState?.getString(KEY_PHOTO_URI)?.let {
            currentPhotoUri = Uri.parse(it)
        }
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observePortfolio()
        binding.fabAddPhoto.setOnClickListener {
            showImageSourceDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentPhotoUri?.let {
            outState.putString(KEY_PHOTO_URI, it.toString())
        }
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("📷 Take Photo", "🖼️ Choose from Gallery")
        AlertDialog.Builder(requireContext())
            .setTitle("Add Photo")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> checkCameraPermission()
                    1 -> openGallery()
                }
            }
            .show()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            requestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        currentPhotoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            photoFile
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        }
        takePicture.launch(intent)
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        pickImage.launch(intent)
    }

    private fun setupRecyclerView() {
        adapter = PortfolioAdapter(emptyList()) { item -> deleteItem(item) }
        binding.rvPortfolio.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPortfolio.adapter = adapter
    }

    private fun observePortfolio() {
        repository.allPortfolioItems.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvPortfolio.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvPortfolio.visibility = View.VISIBLE
                adapter.updateList(items)
            }
        }
    }

    private fun showCaptionDialog(imageUri: String) {
        val editText = EditText(requireContext()).apply {
            hint = "Enter caption (optional)"
            setPadding(48, 32, 48, 32)
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Add Caption")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val caption = editText.text.toString()
                val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
                savePortfolioItem(imageUri, caption, date)
            }
            .setNegativeButton("Skip") { _, _ ->
                val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
                savePortfolioItem(imageUri, "", date)
            }
            .show()
    }

    private fun savePortfolioItem(uri: String, caption: String, date: String) {
        lifecycleScope.launch {
            repository.insertPortfolioItem(
                PortfolioItem(imageUri = uri, caption = caption, date = date)
            )
            Toast.makeText(requireContext(),
                "Photo added!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteItem(item: PortfolioItem) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Photo")
            .setMessage("Are you sure?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    repository.deletePortfolioItem(item)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}