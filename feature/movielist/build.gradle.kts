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