plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(AppDependencies.kotlinStdLib)
    api(project(":domain"))
    api("com.google.code.gson:gson:2.8.6")
    api("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    implementation("androidx.room:room-ktx:2.3.0")
    implementation(AppDependencies.dagger)
    implementation(AppDependencies.coroutines)
    kapt(AppDependencies.daggerCompiler)
    testImplementation(AppDependencies.testLibraries)
}