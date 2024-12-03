package com.example.capstoneproject.View.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.capstoneproject.View.BottomNavigation
import com.example.capstoneproject.View.Register.RegisterActivity
import com.example.capstoneproject.data.remote.saveFirebaseToken
import com.example.capstoneproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val email = binding.emailTextInput.editText?.text.toString().trim()
            val password = binding.PasswordTextInput.editText?.text.toString().trim()

            if (email.isEmpty()){
                binding.emailTextInput.error = "email cannot be empty"
                binding.emailTextInput.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.PasswordTextInput.error = "password cannot be empty"
                binding.PasswordTextInput.requestFocus()
                return@setOnClickListener
            }
           loginUser(email,password)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            reload()
        }
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    saveFirebaseToken(this)
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    reload()
                } else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    Log.e("Login Fail", task.exception?.message.toString())
                }
            }

    }
    private fun reload(){
        val intent = Intent(this, BottomNavigation::class.java)
        startActivity(intent)
        finish()
    }

}