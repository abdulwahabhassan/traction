plugins {
    id("com.traction.android.feature")
    id("com.traction.android.library.compose")
}

android {
    namespace = "com.traction.feature.movielist"
    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
}
