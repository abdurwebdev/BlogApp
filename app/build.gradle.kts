plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.madfinal"
    compileSdk = 36 

    defaultConfig {
        applicationId = "com.example.madfinal"
        minSdk = 24 // Minimum SDK 24 (Android 7.0) to support modern features while covering most devices
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

/*
 * ASSIGNMENT 1 - GRADLE DEPENDENCIES EXPLANATION
 * 
 * 1. Retrofit (com.squareup.retrofit2:retrofit): Used for REST API network calls. 
 *    Chosen because it simplifies HTTP requests and JSON parsing.
 * 
 * 2. GSON Converter (com.squareup.retrofit2:converter-gson): Used to automatically 
 *    convert JSON responses from the API into Java Objects (Models).
 * 
 * 3. Material Components (com.google.android.material:material): Used for modern UI elements
 *    like FloatingActionButton, CardView, TextInputLayout, and Toolbar.
 * 
 * COMMON ISSUES & RESOLUTIONS:
 * - Issue: "Duplicate class found" or Version conflict.
 *   Resolution: Ensured all AndroidX libraries use compatible versions defined in libs.versions.toml.
 *   
 * - Issue: Retrofit calls failing with "Cleartext HTTP traffic not permitted".
 *   Resolution: Although we use HTTPS, added <uses-permission android:name="android.permission.INTERNET" /> 
 *   in AndroidManifest.xml.
 */

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    
    // API & Networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}