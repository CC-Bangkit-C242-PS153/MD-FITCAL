package com.example.capstoneproject.View.Result

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivitySleepResultBinding
class SleepResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySleepResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra("EXTRA_RESULT")
        val activity1 = intent.getStringExtra("EXTRA_ACTIVITY1")
        val reason1 = intent.getStringExtra("EXTRA_REASON1")
        val activity2 = intent.getStringExtra("EXTRA_ACTIVITY2")
        val reason2 = intent.getStringExtra("EXTRA_REASON2")

        binding.tvResult.text = "Kondisi Tidur ANDA = ${result}" ?: "No result available"
        binding.tvActivity1.text = activity1 ?: "No result available"
        binding.tvReason1.text = reason1 ?: "No result available"
        binding.tvActivity2.text = activity2 ?: "No result available"
        binding.tvReason2.text = reason2 ?: "No result available"

    }
}