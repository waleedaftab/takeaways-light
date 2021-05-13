plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.rxJava)
    implementation(AppDependencies.rxKotlin)
    implementation(AppDependencies.dagger)
    kapt(AppDependencies.daggerCompiler)

    testImplementation(AppDependencies.testLibraries)
}