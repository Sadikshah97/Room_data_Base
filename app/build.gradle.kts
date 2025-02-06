plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"   // Add latest version
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")

    id ("kotlin-android")
   // id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.roomdatabase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.roomdatabase"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding=true
    }
}

dependencies {
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment)
    val room_version = "2.6.1" // ✅ Use consistent Room version

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ✅ Room Database Dependencies
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // ✅ Kotlin Extensions (Recommended)
    ksp("androidx.room:room-compiler:$room_version") // ✅ Use KSP only

    androidTestImplementation ("androidx.room:room-testing:2.2.5")

    // Kotlin components
    ksp("com.android.databinding:compiler:3.2.0-alpha10")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    // Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")



//DataBinding



}
