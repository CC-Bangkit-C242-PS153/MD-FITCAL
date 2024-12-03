package com.example.capstoneproject.View.ui.dashboard

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.example.capstoneproject.BuildConfig
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())

fun getImageUri(context: Context): Uri {
    var uri: Uri? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyCamera/")
        }
        uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
    }
    return uri ?: getImageUriForPreQ(context)
}

private fun getImageUriForPreQ(context: Context): Uri {
    val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(filesDir, "/MyCamera/$timeStamp.jpg")
    if (imageFile.parentFile?.exists() == false) imageFile.parentFile?.mkdir()
    return FileProvider.getUriForFile(
        context,
        "${BuildConfig.APPLICATION_ID}.fileprovider",
        imageFile
    )
}
//fun uriToMultipart(context: Context, uri: Uri, fileName: String): MultipartBody.Part? {
//    val file = getFileFromUri(context, uri)
//    if (file != null) {
//        Log.d("erorrnya disini", "File path: ${file.absolutePath}")
//        val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
//        return MultipartBody.Part.createFormData("photo", fileName, requestBody)
//    } else {
//        Log.e("disini", "Failed to get file from URI")
//    }
//    return null
//}

//// Fungsi untuk mendapatkan file dari Uri
//fun getFileFromUri(context: Context, uri: Uri): File? {
//    var file: File? = null
//    try {
//        // Mendapatkan InputStream untuk Uri
//        val inputStream = context.contentResolver.openInputStream(uri)
//        if (inputStream != null) {
//            // Menyimpan InputStream ke file sementara
//            val tempFile = File(context.cacheDir, "temp_image.jpg")
//            val outputStream = tempFile.outputStream()
//            inputStream.copyTo(outputStream)
//            file = tempFile
//            Log.d("diFileFromUri", "Temp file created at: ${tempFile.absolutePath}")
//        }
//    } catch (e: IOException) {
//        Log.e("diFileFromUri", "Error while getting file from URI: ${e.message}")
//    }
//    return file
//}
