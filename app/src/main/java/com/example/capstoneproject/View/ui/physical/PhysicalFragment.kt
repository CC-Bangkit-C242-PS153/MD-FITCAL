package com.example.capstoneproject.View.ui.physical

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.View.Result.PhysicalResultActivity
import com.example.capstoneproject.View.Result.SleepResultActivity
import com.example.capstoneproject.View.ui.sleepcycle.SleepCycleFragment.Companion.TAG
import com.example.capstoneproject.View.ui.sleepcycle.SleepCycleViewModel
import com.example.capstoneproject.data.remote.ViewModelFactory
import com.example.capstoneproject.data.remote.modelRequest.PhysicalRequest
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest
import com.example.capstoneproject.data.remote.retrofit.ApiConfig
import com.example.capstoneproject.databinding.FragmentPhysicalBinding


class PhysicalFragment : Fragment() {

    private var _binding: FragmentPhysicalBinding? = null
    private lateinit var physicalViewModel: PhysicalViewModel


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhysicalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        physicalViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiConfig.getApiService(context))
        )[PhysicalViewModel::class.java]

        val genderList = listOf(0,1)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter


        binding.submitButton.setOnClickListener {
            val selectedGender = binding.spinnerGender.selectedItem.toString()
            Log.d(TAG, "Selected gender: $selectedGender")
            val age = binding.editTextAge.text.toString()
            val height = binding.editTextHeight.text.toString()
            val weight = binding.editTextWeight.text.toString()
            val duration = binding.editTextDuration.text.toString()
            val heartRate = binding.editTextHeartRate.text.toString()
            val bodyTemp = binding.editTextBodyTemp.text.toString()

            if (age.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()
                && duration.isNotEmpty() && heartRate.isNotEmpty() && bodyTemp.isNotEmpty()) {

                val physicalRequest = PhysicalRequest(
                    gender = selectedGender.toInt(),
                    age = age.toInt(),
                    height = height.toInt(),
                    weight = weight.toInt(),
                    duration = duration.toInt(),
                    heartRate = heartRate.toInt(),
                    bodyTemp = bodyTemp.toDouble()

                )

                observePrediction(physicalRequest)
            } else {
            }
        }
    }


    private fun observePrediction(physicalRequest: PhysicalRequest) {
        physicalViewModel.predictPhysical(physicalRequest).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                Log.d(TAG, "Response: ${response}")

                val result = response.result

                val intent = Intent(requireContext(), PhysicalResultActivity::class.java).apply {
                    putExtra("EXTRA_RESULT", result?.result)
                    putExtra("EXTRA_ACTIVITY1", result?.suggestion?.activities1?.activity)
                    putExtra("EXTRA_ACTIVITY2", result?.suggestion?.activities2?.activity)
                    putExtra("EXTRA_REASON1", result?.suggestion?.activities1?.reason)
                    putExtra("EXTRA_REASON2", result?.suggestion?.activities2?.reason)
                }
                startActivity(intent)


            } else {
                Log.e(TAG, "Response is null")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}