# Coina

> Cryptocurrency Mobile Application

![](https://github.com/Yazan98/Coina/blob/main/resources/Screenshot%202022-12-30%20at%2010.32.59%20AM.png?raw=true)

![](https://github.com/Yazan98/Coina/blob/main/resources/Screenshot%202022-12-30%20at%2011.29.07%20AM.png?raw=true)

## Description

This Project is a Kotlin Multiplatform Mobile Application That Built on Android, IOS Platforms to show Cryptocurrencies Information Based on Free [Api](https://www.coingecko.com/en/api/documentation)
This Application is a Simple Demo To See how i can Integrate Shared Logic Between 2 Platforms for Saved Data With/Without Session, Api Requests, Database Queries

## Shared Parts
This Application has Shared Module that Contains The code Shared Between 2 Platforms

1. Database Queries (Write, Read, Custom Queries)
2. Key, Value Store (UserDefaults, SharedPrefs)
3. Api Requests (All APIs)
4. UseCases (Each UseCase for Each Screen)

## Application Features
1. Login Screen
2. Home Screen
3. Coins List
4. Exchanges List
5. Categories List
6. Category Coins List

## Components Used to Build this App
Shared Module
1. Ktor Client
2. Realm Kotlin SDK
3. Kotlinx-Serialization
4. Native cocoapods

Android Modules
1. Multi Modular App (Each Feature has it's own Module)
2. Jetpack Compose
3. Material 3
4. Ktor Android Client
5. Lifecycle - ViewModels
6. Hilt
7. Kapt
8. Google Play Services
9. Jetpack Compose - Navigation Components
10. Jetpack Compose Accompanist - UI Controller
11. Coil - Image Loading
12. Timber
13. MultiDexMerge
14. Firebase
15. MPAndroidChart

IOS App
1. SwiftUI
2. Google MaterialComponents - Snackbar
3. ObjectMapper
4. Shared Module
5. Cocoapods Dependencies
6. Ktor Darwin Client

## Application Training Parts
1. Auth Screen Exists to Test (SharedPrefs, UserDefault) with same Key Value Manager
2. Coins List Simulate Multiple UseCases and Actions in one ViewModel
3. Coins List Simulate Same Database Query to Search in Coins
4. Categories List Simulate Navigation Between Screens in Each Platform
5. Auth, Home Screens Support Dark, White Mode in each Platform
6. Project Structure Built Based on cocoapods to Import Shared Library 

## Application Flow Information

1. User Flow in The Application

![](https://github.com/Yazan98/Coina/blob/main/resources/User%20Flow.jpg?raw=true)

2. Feature Flow in Each Screen

![](https://github.com/Yazan98/Coina/blob/main/resources/Feature%20Flow.jpg?raw=true)

## Screenshots Information

1. [Application Images](https://github.com/Yazan98/Coina/tree/main/resources)
2. [Video Compare Android, IOS](https://github.com/Yazan98/Coina/blob/main/resources/Compare%20Platforms.mp4)

## IDEs Requirements
1. Android Studio Version: Android Studio Flamingo | 2022.2.1 Canary 5
2. XCode Version: Version 14.2 (14C18)

## Steps to Run the Project
```
1. Open Android Studio with Root Application
2. Open Terminal and Execute (pod install)

If Gradle Build Failed for First Time
3. Execute Command (cd iosApp)
4. Execute Command (pod install)

5. Run The Android App
6. Open XCode with iosApp Folder
7. Execute Command (pod update shared)
8. Run IOS App
```

## Useful Resources

1. [Key Value Store](https://medium.com/@shmehdi01/shared-preference-in-kmm-kotlin-multiplatform-2bca14214093)
2. [SwiftUI](https://www.youtube.com/watch?v=TTYKL6CfbSs&list=PLwvDm4Vfkdphbc3bgy_LpLRQ9DDfFGcFu)
3. [Shared ViewModels](https://proandroiddev.com/kotlin-multiplatform-mobile-and-how-share-viewmodel-an-architecture-proposal-b6f86b61abf9)
4. [Shared ViewModels](https://medium.com/double-symmetry/kotlin-multiplatform-tales-a-shared-viewmodel-f9d0792f69f9)
5. [Ktor Documentation](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html)
6. [KMM Documentation](https://kotlinlang.org/docs/multiplatform-mobile-ktor-sqldelight.html)
7. [Api Link](https://www.coingecko.com/en/api/documentation)
8. [Android Charts Library](https://github.com/PhilJay/MPAndroidChart)
9. [Realm Kotlin SDK](https://www.mongodb.com/docs/realm/sdk/kotlin/install/kotlin-multiplatform/)
10. [XCode-Kotlin Plugin](https://github.com/touchlab/xcode-kotlin)