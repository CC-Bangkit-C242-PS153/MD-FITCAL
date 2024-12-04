package com.example.capstoneproject.View.Result

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ActivityPhysicalResultBinding

class PhysicalResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPhysicalResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhysicalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val result = intent.getStringExtra("EXTRA_RESULT")
        val activity1 = intent.getStringExtra("EXTRA_ACTIVITY1")
        val reason1 = intent.getStringExtra("EXTRA_REASON1")
        val activity2 = intent.getStringExtra("EXTRA_ACTIVITY2")
        val reason2 = intent.getStringExtra("EXTRA_REASON2")

        binding.tvResult.text = "Jumlah Kalori Yang Di bakar = ${result}" ?: "No result available"
        binding.tvActivity1.text = activity1 ?: "No result available"
        binding.tvReason1.text = reason1 ?: "No result available"
        binding.tvActivity2.text = activity2 ?: "No result available"
        binding.tvReason2.text = reason2 ?: "No result available"
    }
}