plugins {
    id("com.traction.android.library")
    id("com.traction.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "com.traction.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    kapt(libs.hilt.compiler)
    api(libs.androidx.paging.runtime)
}
