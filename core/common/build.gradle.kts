plugins {
    id("com.traction.android.library")
    id("com.traction.android.hilt")
}

android {
    namespace = "com.traction.core.common"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.coroutines.android)
}