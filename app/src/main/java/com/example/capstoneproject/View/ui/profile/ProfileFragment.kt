package com.example.capstoneproject.View.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.capstoneproject.R
import com.example.capstoneproject.View.Login.LoginActivity
import com.example.capstoneproject.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.actionBar?.hide()
        super.onCreate(savedInstanceState)
        auth = Firebase.auth


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        loadUserProfile()
        binding.logoutButton.setOnClickListener {
            logout()
        }


        return binding.root
    }

    private fun logout() {
        auth.signOut()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()

    }

    private fun loadUserProfile() {
        val userId = auth.currentUser?.uid // Dapatkan UID pengguna saat ini
        if (userId != null) {
            val db = Firebase.firestore
            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        // Ambil data dari Firestore
                        val name = document.getString("name") ?: "Nama tidak tersedia"
                        val email = document.getString("email") ?: "Email tidak tersedia"
                        val gender = document.getString("gender") ?: "Jenis kelamin tidak tersedia"
                        val dateOfBirth =
                            document.getString("dateOfBirth") ?: "Tanggal lahir tidak tersedia"

                        // Set data ke TextView
                        binding.nameEditText.text = name
                        binding.emailEditText.text = email
                        binding.genderEditText.text = gender
                        binding.dobEditText.text = dateOfBirth
                    } else {
                        // Jika dokumen tidak ditemukan
                        Toast.makeText(
                            requireContext(),
                            "Profil tidak ditemukan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { exception ->
                    // Tangani error saat mengambil data
                    Toast.makeText(
                        requireContext(),
                        "Gagal memuat profil: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            // Jika pengguna tidak login
            Toast.makeText(requireContext(), "Pengguna tidak terautentikasi", Toast.LENGTH_SHORT)
                .show()
        }
    }


}