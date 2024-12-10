# **Capstone Project: Fitcal**

<div align="center">
  <img 
    src="https://github.com/user-attachments/assets/d8fb8dda-7064-4d99-8837-3c4ec51c9023" 
    alt="Banner" 
    style="border-radius: 10px; max-width: 80%; height: auto;"
  />
</div>

---

## **📖 Deskripsi**
<div align="justify">
Bagian Mobile Development bertanggung jawab untuk membangun aplikasi yang memungkinkan pengguna memanfaatkan fitur-fitur utama seperti prediksi kalori, analisis pola tidur, dan pelacakan aktivitas fisik.
</div>

---

## **🔥 Fitur App Fitcal**
- **📱 Registrasi dan Login**: Membuat akun baru dan login menggunakan email.
- **🍛 Prediksi Kalori**: Mendapatkan prediksi kalori dari makanan dengan mengunggah foto.
- **🌙 Analisis Pola Tidur**: Rekomendasi pola tidur yang sehat berdasarkan data pengguna.
- **🏃 Pelacakan Aktivitas Fisik**: Melacak aktivitas fisik dan memberikan saran sesuai aktivitas pengguna.

---

## **📋 Implementasi Fitur Utama**

### **🔑 Workflow Akses Fitur Utama**
- Implementasi alur kerja aplikasi yang memungkinkan pengguna untuk mengakses fitur utama secara langsung dan intuitif.
- Alur mencakup proses **autentikasi pengguna**, navigasi ke fitur utama seperti **prediksi kalori**, **analisis pola tidur**, dan **pelacakan aktivitas fisik**.

### **🧠 Integrasi AI/ML**
- Mengintegrasikan kemampuan **AI/ML** sebagai fitur utama aplikasi, baik melalui layanan cloud maupun pemrosesan langsung di perangkat.
- Contoh penerapan:
  - **Prediksi Kalori**: Memanfaatkan model ML untuk menganalisis gambar makanan yang diunggah pengguna dan menghasilkan estimasi kalori.
  - **Analisis Pola Tidur**: Menggunakan algoritme berbasis data untuk memberikan rekomendasi pola tidur yang sehat.
  - **Pelacakan Aktivitas Fisik**: Menggunakan AI untuk menganalisis data dari sensor perangkat (seperti langkah dan jarak tempuh), menghitung kalori yang terbakar, dan memberikan notifikasi pengingat aktivitas untuk menjaga kesehatan pengguna.


### **🌐 Networking API**
- Implementasi pemanggilan jaringan menggunakan **Retrofit** untuk berinteraksi dengan API proyek.
- API digunakan untuk mengakses data seperti prediksi kalori, pola tidur, dan pelacakan aktivitas fisik secara real-time.

### **🚫 Menjamin Stabilitas Aplikasi**
- Memastikan implementasi fitur utama berjalan tanpa menyebabkan aplikasi crash.
- Penggunaan validasi input dan penanganan error yang optimal untuk menjaga pengalaman pengguna tetap mulus.

### **🎨 Ikon Aplikasi Kustom**
- Menambahkan ikon aplikasi khusus untuk memberikan identitas visual yang unik.

### **📥 Penyediaan APK**
- Menyediakan file **APK** yang dapat diunduh untuk mempermudah pengujian aplikasi di perangkat pengguna.

---

## **🎨 Mockup Aplikasi**
- Membuat representasi visual desain dan antarmuka pengguna aplikasi menggunakan tools seperti **Figma** atau **Adobe XD**.
- Mockup ini meliputi layar-layar utama:
  - Halaman **Login** dan **Registrasi**.
  - Halaman **Prediksi Kalori**.
  - Halaman **Analisis Pola Tidur**.
  - Dashboard untuk **Pelacakan Aktivitas Fisik**.

---

## **📐 Arsitektur MVVM**
- Menggunakan arsitektur **Model-View-ViewModel (MVVM)** di seluruh proyek untuk:
  - Memisahkan logika bisnis dari tampilan.
  - Mempermudah pengelolaan kode dan pengujian unit.
  - Menggunakan **LiveData** untuk mengikat data ke UI secara reaktif.

---

## **🔐 Fitur Autentikasi**
### **Login dan Registrasi**
- Menambahkan fitur **Login** dan **Registrasi** menggunakan layanan seperti:
  - **Firebase Authentication**.
  - Custom Authentication API yang disediakan oleh proyek.
- Fitur ini memastikan bahwa pengguna dapat mengakses aplikasi dengan akun yang aman.

### **Autentikasi OTP**
- Mengimplementasikan autentikasi **One-Time Password (OTP)** untuk meningkatkan keamanan login pengguna.
- OTP dikirimkan melalui email atau SMS untuk memverifikasi identitas pengguna.

---

## **🚀 Teknologi dan Library yang Digunakan**

| **Teknologi**  | **Deskripsi**                                            |
|-----------------|----------------------------------------------------------|
| Kotlin          | Bahasa pemrograman utama untuk pengembangan aplikasi.   |
| Retrofit        | Library untuk komunikasi API.                           |
| Glide           | Mengelola gambar di aplikasi.                          |
| Room            | Database lokal untuk penyimpanan data pengguna.         |
| Firebase        | Layanan cloud untuk autentikasi dan penyimpanan data pengguna.|

---

## **🛠️ Instalasi**
Ikuti langkah-langkah di bawah ini untuk menjalankan proyek secara lokal:

1. Clone repository:
   ```bash
   git clone https://github.com/CC-Bangkit-C242-PS153/MD-FITCAL
  - Jalankan proyek di emulator atau perangkat nyata
  - Sinkronkan semua dependensi Gradle
  - Jalankan proyek di emulator atau perangkat nyata


  ---
## 📂 Struktur Folder
Struktur folder proyek ini adalah sebagai berikut:

```plaintext
├── data/
│   ├── di/                     # Dependency Injection
│   ├── remote/
│       ├── modelRequest/       # Model request untuk API
│       ├── response/           # Model respons API
│       ├── retrofit/           # Konfigurasi Retrofit
│
├── View/
│   ├── CustomView/             # Custom View komponen UI
│   ├── Login/                  # Fitur Login
│   ├── Main/                   # Halaman utama aplikasi
│   ├── Register/               # Halaman Registrasi
│   ├── Result/                 # Hasil analisis
│   ├── ui/
│       ├── dashboard/          # Dashboard utama
│       ├── physical/           # Pelacakan aktivitas fisik
│       ├── profile/            # Profil pengguna
│       ├── sleepcycle/         # Analisis pola tidur
│   ├── BottomNavigation/       # Navigasi utama aplikasi
├── res/                         # Resources
│   ├── drawable/               # Gambar yang digunakan di aplikasi
│   ├── layout/                 # Layout XML untuk UI
│   ├── menu/                   # Menu XML
│   ├── mipmap/                 # Ikon aplikasi
│   ├── navigation/             # Navigasi
│   ├── values/                 # File values (strings, colors, dimens, styles)
│   ├── xml/                    # File XML lainnya


📦 dependencies
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.gridlayout)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("id.zelory:compressor:3.0.1")
    implementation ("com.google.android.material:material:1.9.0")

}

