package com.example.capstoneproject.data.remote

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

fun getFirebaseToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("firebase_token", null)
}


fun saveFirebaseToken(context: Context) {
    FirebaseAuth.getInstance().currentUser?.getIdToken(true)?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val token:String? = task.result?.token
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            // Menyimpan token di SharedPreferences
            sharedPreferences.edit().putString("firebase_token", token).apply()
            Log.d("Token:", token.toString())
        } else {
            // Tangani error jika task gagal
            Log.e("Token Error", task.exception?.message ?: "Unknown error")
        }
    }
}

