# **Capstone Project: Fitcal**

<div align="center">
  <img 
    src="https://github.com/user-attachments/assets/d8fb8dda-7064-4d99-8837-3c4ec51c9023" 
    alt="Banner" 
    style="border-radius: 10px; max-width: 80%; height: auto;"
  />
</div>

---

## **📖 Description**
The Mobile Development team is responsible for building an application that enables users to utilize key features such as calorie prediction, sleep pattern analysis, and physical activity tracking.

---

## **🔥 Features of Fitcal App**
- **📱 Registration and Login**: Allows users to create a new account and log in using their email.
- **🍛 Calorie Prediction**: Provides calorie prediction by uploading a food image.
- **🌙 Sleep Pattern Analysis**: Recommends healthy sleep patterns based on user data.
- **🏃 Physical Activity Tracking**: Tracks physical activity and provides suggestions based on user activity.

---

## **📋 Implementation of Main Features**

### **🔑 Workflow to Access Main Features**
- Implementation of an application workflow that enables users to access main features directly and intuitively.
- The workflow includes **user authentication**, navigation to key features such as **calorie prediction**, **sleep pattern analysis**, and **physical activity tracking**.

### **🧠 AI/ML Integration**
- Integration of **AI/ML** capabilities as the main feature of the application, either through cloud services or on-device processing.
- Examples of implementation:
  - **Calorie Prediction**: Utilizes ML models to analyze food images uploaded by users and generate calorie estimates.
  - **Sleep Pattern Analysis**: Uses data-driven algorithms to provide recommendations for healthy sleep patterns.
  - **Physical Activity Tracking**: Employs AI to analyze sensor data (e.g., steps and distance), calculate burned calories, and send reminders to keep users active for better health.

### **🌐 Networking API**
- Implementation of networking calls using **Retrofit** to interact with the project's API.
- The API is used to access real-time data such as calorie predictions, sleep patterns, and physical activity tracking.

### **🚫 Ensuring Application Stability**
- Ensures that the main features are implemented without causing application crashes.
- Validates inputs and optimizes error handling to maintain a seamless user experience.

### **🎨 Custom App Icon**
- Adds a custom application icon to provide a unique visual identity.

### **📥 APK Provision**
- Provides a downloadable **APK** file to facilitate testing on user devices.

---

## **🎨 App Mockup**
- Creates a visual representation of the app’s design and user interface using tools such as **Figma** or **Adobe XD**.
- The mockup includes key screens:
  - **Login** and **Registration** pages.
  - **Calorie Prediction** page.
  - **Sleep Pattern Analysis** page.
  - Dashboard for **Physical Activity Tracking**.

---

## **📐 MVVM Architecture**
- Implements the **Model-View-ViewModel (MVVM)** architecture throughout the project to:
  - Separate business logic from the UI.
  - Simplify code management and unit testing.
  - Utilize **LiveData** for reactive data binding to the UI.

---

## **🔐 Authentication Features**

### **Login and Registration**
- Adds **Login** and **Registration** features using services like:
  - **Firebase Authentication**.
  - Custom Authentication API provided by the project.
- This feature ensures that users can access the application securely with their accounts.

### **OTP Authentication**
- Implements **One-Time Password (OTP)** authentication to enhance user login security.
- OTP is sent via email or SMS to verify the user's identity.

---

## **🚀 Technology and Libraries Used**

| **Technology**  | **Description**                                         |
|-----------------|----------------------------------------------------------|
| Kotlin          | Primary programming language for app development.       |
| Retrofit        | Library for API communication.                          |
| Glide           | Handles image processing in the app.                   |
| Room            | Local database for storing user data.                  |
| Firebase        | Cloud service for authentication and data storage.     |

---

## **🛠️ Installation**

Follow these steps to run the project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/CC-Bangkit-C242-PS153/MD-FITCAL
      -Open the project in Android Studio:
        -Open Android Studio.
        -Select Open Project.
        -Navigate to the folder where the repository was cloned and click OK.

---
## 📂 Struktur Folder
├── data/
│   ├── di/                     # Dependency Injection
│   ├── remote/
│       ├── modelRequest/       # API request models
│       ├── response/           # API response models
│       ├── retrofit/           # Retrofit configurations
│
├── View/
│   ├── CustomView/             # Custom UI components
│   ├── Login/                  # Login feature
│   ├── Main/                   # Main screen
│   ├── Register/               # Registration screen
│   ├── Result/                 # Analysis results
│   ├── ui/
│       ├── dashboard/          # Main dashboard
│       ├── physical/           # Physical activity tracking
│       ├── profile/            # User profile
│       ├── sleepcycle/         # Sleep pattern analysis
│   ├── BottomNavigation/       # Main navigation
├── res/                         # Resources
│   ├── drawable/               # App images
│   ├── layout/                 # XML layouts for UI
│   ├── menu/                   # Menu XML files
│   ├── mipmap/                 # App icons
│   ├── navigation/             # Navigation files
│   ├── values/                 # Values (strings, colors, dimensions, styles)
│   ├── xml/                    # Other XML resources


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

