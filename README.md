<h1 align="center">Traction</h1>

<p align="center">
  <a href="https://www.android.com/"><img alt="Platform" src="https://img.shields.io/badge/Platform-Android-white"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-yellow.svg?style=flat"/></a>
  <a href="https://github.com/abdulwahabhassan/carbon/actions"><img alt="Build Status" src="https://github.com/abdulwahabhassan/traction/workflows/Build/badge.svg"/></a> 
</p>

<p align="center">Traction keeps you up to date with the latest and trending movies</p>

## Preview

|            |            |            |            |
|------------|------------|------------|------------|
| ![img one](img_one.png) | ![img two](img_two.png) | ![img three](img_three.png) | ![img four](img_five.png) |

## Configurations
- Minimum SDK level 26
- Compile SDK version 33
- Targeted SDK version 33
- Current version code 1
- Current version name "1.0.0"

## Tech stack & Third-party libraries
- [Kotlin](https://kotlinlang.org/), [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations and background processes.
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle-aware. Allows data to survive configuration changes such as screen rotations.
  - Jetpack Compose - Declarative UI
  - Room - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access
  - Hilt - Manage dependency injection.
- Architecture
  - MVVM Architecture (Model - View - ViewModel)
  - Repository Pattern
  - Multi-modular (app, feature, core)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components for building ripple animation, and CardView.
  
