package com.example.capstoneproject.View.ui.dashboard

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.capstoneproject.View.ui.sleepcycle.SleepCycleFragment.Companion.TAG
import com.example.capstoneproject.data.remote.ViewModelFactory
import com.example.capstoneproject.data.remote.retrofit.ApiConfig
import com.example.capstoneproject.databinding.FragmentCaloriesBinding
import kotlinx.coroutines.launch

import java.io.File

class CaloriesFragment : Fragment() {

    private var _binding: FragmentCaloriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var caloriesViewModel: CaloriesViewModel
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaloriesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        caloriesViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiConfig.getApiService(requireContext()))
        )[CaloriesViewModel::class.java]

        if (savedInstanceState != null) {
            currentImageUri = savedInstanceState.getParcelable("imageUri")
            showImage()
        }

        binding.submitButton.setOnClickListener {
            val water = binding.edtWater.text.toString()
            val protein = binding.edtProtein.text.toString()
            val lipid = binding.edtLiquid.text.toString()
            val ash = binding.edtAsh.text.toString()
            val carbohydrate = binding.edtCarbohydrate.text.toString()
            val fiber = binding.edtFiber.text.toString()
            val sugar = binding.edtSugar.text.toString()


            if (water.isNotEmpty() && protein.isNotEmpty() && lipid.isNotEmpty() &&
                ash.isNotEmpty() && carbohydrate.isNotEmpty() &&
                fiber.isNotEmpty() && sugar.isNotEmpty() && currentImageUri != null
            ) {

                val photoPart = uriToFilePath(currentImageUri!!)
                if (photoPart != null) {
                    val file = File(photoPart)
                    lifecycleScope.launch {
                        observePrediction(
                            file,
                            water.toDouble(),
                            protein.toDouble(),
                            lipid.toDouble(),
                            ash.toDouble(),
                            carbohydrate.toDouble(),
                            fiber.toDouble(),
                            sugar.toDouble()
                        )
                    }
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Anda belum menyertakan foto", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Failed to prepare photo part")
                }
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),"Mohon untuk mengisi semua field", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Please fill all fields and select an image")
            }
        }


        binding.edtImage.setOnClickListener {
            startCamera()
        }

        return root
    }

    private fun observePrediction(
        photo: File,
        water: Number,
        protein: Number,
        lipid: Number,
        ash: Number,
        carbohydrate: Number,
        fiber: Number,
        sugar: Number
    ) {
        binding.progressBar.visibility =View.VISIBLE
        caloriesViewModel.predictCalories(
            photo,
            water,
            protein,
            lipid,
            ash,
            carbohydrate,
            fiber,
            sugar
        ).observe(viewLifecycleOwner) { response ->

            if (response != null) {
                binding.progressBar.visibility = View.GONE
                binding.lnLayoutResultFoodCalories.visibility = View.VISIBLE
                binding.lnLayoutSuggestion.visibility = View.VISIBLE
                Log.d(TAG, "Response: $response")
                val result = response.result
                binding.tvCaloriesResult.text = "${result?.foodCalories.toString()} gram"
                binding.tvFoodResult.text = result?.foodCategory.toString()
                binding.edtSuggestion1.text = result?.suggestion?.activities1?.activity.toString()
                binding.edtReason1.text = result?.suggestion?.activities1?.reason.toString()
                binding.edtSuggestion2.text = result?.suggestion?.activities2?.activity.toString()
                binding.edtReason2.text = result?.suggestion?.activities2?.reason.toString()
            } else {
                binding.lnLayoutResultFoodCalories.visibility = View.GONE
                binding.lnLayoutSuggestion.visibility = View.GONE
                Toast.makeText(requireContext(), "Mohon Maaf Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Response is null")
            }
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        if (currentImageUri != null) {
            Log.d(TAG, "Camera URI: $currentImageUri")
            launcherIntentCamera.launch(currentImageUri)
        } else {
            Log.e(TAG, "Failed to create image URI")
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        } else {
            currentImageUri = null
            Log.e(TAG, "Camera capture failed")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d(TAG, "Show image URI: $it")
            binding.edtImage.setImageURI(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun uriToFilePath(uri: Uri): String? {
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1) {
                it.moveToFirst()
                return it.getString(columnIndex)
            }
        }
        return null
    }
}
