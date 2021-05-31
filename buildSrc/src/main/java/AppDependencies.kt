import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerAndroidCompiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val material = "com.google.android.material:material:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    private val junit = "junit:junit:${Versions.junit}"
    private val mockito = "org.mockito:mockito-core:2.19.0"
    private val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"

    private val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    private val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"

    private val activityKtx = "androidx.activity:activity-ktx:${Versions.activity_version}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(activityKtx)
        add(dagger)
        add(daggerAndroid)
        add(daggerAndroidSupport)
        add(appcompat)
        add(material)
        add(constraintLayout)
        add(viewModel)
        add(liveData)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockito)
        add(mockitoKotlin)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}