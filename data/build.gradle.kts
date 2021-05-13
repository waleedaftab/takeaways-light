plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(AppDependencies.kotlinStdLib)
    api(project(":domain"))
    api("com.google.code.gson:gson:2.8.6")
    api("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-rxjava3:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    implementation(AppDependencies.rxJava)
    implementation(AppDependencies.rxKotlin)
    implementation(AppDependencies.dagger)
    kapt(AppDependencies.daggerCompiler)

    testImplementation(AppDependencies.testLibraries)
}