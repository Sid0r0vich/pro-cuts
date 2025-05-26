plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.20"

    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.sidor.procuts"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sidor.procuts"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("androidx.compose.material:material-icons-extended-android:1.7.8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Dependency injection
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:2.56.1")
    kapt("com.google.dagger:hilt-android-compiler:2.56.1")

    // Phone number parser
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.50")

    // Camera
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0n")
    implementation("androidx.camera:camera-view:1.3.0")
    implementation("androidx.camera:camera-extensions:1.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.37.3")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")
    implementation("androidx.fragment:fragment-ktx:1.8.7")

    // Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("io.coil-kt:coil-compose:2.7.0")
}