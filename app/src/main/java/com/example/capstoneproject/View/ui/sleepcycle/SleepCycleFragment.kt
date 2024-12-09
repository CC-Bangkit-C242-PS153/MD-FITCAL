package com.example.capstoneproject.View.ui.sleepcycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.View.Result.SleepResultActivity
import com.example.capstoneproject.data.remote.ViewModelFactory
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest
import com.example.capstoneproject.data.remote.retrofit.ApiConfig
import com.example.capstoneproject.databinding.FragmentSleepcycleBinding


class SleepCycleFragment : Fragment() {

    private var _binding: FragmentSleepcycleBinding? = null
    private val binding get() = _binding!!
    private lateinit var sleepCycleViewModel: SleepCycleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSleepcycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        sleepCycleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiConfig.getApiService(context))
        )[SleepCycleViewModel::class.java]

        val genderList = listOf("Laki Laki", "Perempuan")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter


        binding.submitButton.setOnClickListener {
            val selectedGender = binding.spinnerGender.selectedItemPosition
            val genderValue = when (selectedGender) {
                0 -> 1
                1 -> 0
                else -> -1
            }
            val age = binding.editTextAge.text.toString()
            val sleepDuration = binding.editTextSleepDuration.text.toString()
            val qualitySleep = binding.editTextQualitySleep.text.toString()
            val stressLevel = binding.editTextStressLevel.text.toString()
            val physicalActivityLevel = binding.editTextPhysicalActivity.text.toString()
            val bmi = binding.editTextBMI.text.toString()
            val heartRate = binding.editTextHeartRate.text.toString()
            val dailySteps = binding.editTextDailySteps.text.toString()
            val systolicPressure = binding.editTextSystolic.text.toString()
            val diastolicPressure = binding.editTextDiastolic.text.toString()

            if (age.isNotEmpty() && sleepDuration.isNotEmpty() && qualitySleep.isNotEmpty()
                && stressLevel.isNotEmpty() && physicalActivityLevel.isNotEmpty() && bmi.isNotEmpty() && heartRate.isNotEmpty()
                && dailySteps.isNotEmpty() && systolicPressure.isNotEmpty() && diastolicPressure.isNotEmpty()
            ) {
                val errors = mutableListOf<String>()

                if (qualitySleep.toInt() !in 1..10) {
                    errors.add("Quality sleep harus di antara 1 dan 10")
                }
                if (stressLevel.toInt() !in 1..10) {
                    errors.add("Stress level harus di antara 1 dan 10")
                }
                if (physicalActivityLevel.toInt() !in 1..10) {
                    errors.add("Physical activity level harus di antara 1 dan 10")
                }
                if (errors.isEmpty()) {
                    binding.progressBar.visibility = View.VISIBLE
                    val sleepRequest = SleepRequest(
                        gender = genderValue,
                        age = age.toInt(),
                        sleepDuration = sleepDuration.toDouble(),
                        qualitySleep = qualitySleep.toInt(),
                        stressLevel = stressLevel.toInt(),
                        physicalActivity = physicalActivityLevel.toInt(),
                        BMI = bmi.toInt(),
                        heartRate = heartRate.toInt(),
                        dailySteps = dailySteps.toInt(),
                        systolic = systolicPressure.toInt(),
                        diastolic = diastolicPressure.toInt()
                    )
                    observePrediction(sleepRequest)
                } else {

                    val errorMessage = errors.joinToString("\n")
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Semua field harus disisi", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun observePrediction(sleepRequest: SleepRequest) {
        sleepCycleViewModel.predictSleep(sleepRequest).observe(viewLifecycleOwner) { response ->
            if (response != null) {
                binding.progressBar.visibility = View.GONE
                Log.d(TAG, "Response: ${response}")

                val result = response.result

                val intent = Intent(requireContext(), SleepResultActivity::class.java).apply {
                    putExtra("EXTRA_RESULT", result?.result)
                    putExtra("EXTRA_ACTIVITY1", result?.suggestion?.activities1?.activity)
                    putExtra("EXTRA_ACTIVITY2", result?.suggestion?.activities2?.activity)
                    putExtra("EXTRA_REASON1", result?.suggestion?.activities1?.reason)
                    putExtra("EXTRA_REASON2", result?.suggestion?.activities2?.reason)
                }
                startActivity(intent)

            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Mohon Maaf Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Response is null")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "Hasil : "
    }
}
