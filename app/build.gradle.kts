plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.android105class"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android105class"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {
    val nav_version = "2.7.7"
    val room_version = "2.6.1"
    val lifecycle_version = "2.6.2"

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    //Recyclerview
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    //Circle image
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Room
    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
//    //Dagger Hilt
//    implementation("com.google.dagger:hilt-android:2.50")
//    kapt("com.google.dagger:hilt-android-compiler:2.50")
    //Dagger2
    implementation ("com.google.dagger:dagger:2.50")
    kapt ("com.google.dagger:dagger-compiler:2.50")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
kapt{
    correctErrorTypes=true
}