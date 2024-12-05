package com.example.capstoneproject.View.Register

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.R
import com.example.capstoneproject.View.BottomNavigation
import com.example.capstoneproject.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            val name = binding.edtNameRegister.editText?.text.toString().trim()
            val dateOfBirth = binding.edtDateOfBirth.editText?.text.toString().trim()
            val gender = binding.edtGender.editText?.text.toString().trim()
            val email = binding.emailTextInput.editText?.text.toString().trim()
            val password = binding.passwordTextInput.editText?.text.toString().trim()

            if (name.isEmpty()) {
                binding.edtNameRegister.error = "Name cannot be empty"
                binding.edtNameRegister.requestFocus()
                return@setOnClickListener
            }
            if (dateOfBirth.isEmpty()) {
                binding.edtDateOfBirth.error = "Date of Birth cannot be empty"
                binding.edtDateOfBirth.requestFocus()
                return@setOnClickListener
            }
            if (gender.isEmpty()) {
                binding.edtGender.error = "Gender cannot be empty"
                binding.edtGender.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.emailTextInput.error = "Email cannot be empty"
                binding.emailTextInput.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.passwordTextInput.error = "Password cannot be empty"
                binding.passwordTextInput.requestFocus()
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            registerUser(name, dateOfBirth, gender, email, password)
        }

    }

    private fun registerUser(
        name: String, dateOfBirth: String, gender: String,
        email: String, password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        saveUserData(userId, name, dateOfBirth, gender, email)
                    }
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this, "SignUp failed: ${task.exception?.message}", Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, task.exception?.message.toString())
                }
            }
    }
    private fun saveUserData(
        userId: String, name: String, dateOfBirth: String, gender: String, email: String
    ) {
        val userMap = hashMapOf(
            "name" to name,
            "dateOfBirth" to dateOfBirth,
            "gender" to gender,
            "email" to email
        )

        Firebase.firestore.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                reload()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            reload()
        }
    }

    private fun reload(){
        val intent = Intent(this,BottomNavigation::class.java )
        startActivity(intent)
        finish()
    }

    companion object{
        val TAG = "Error"
    }

}