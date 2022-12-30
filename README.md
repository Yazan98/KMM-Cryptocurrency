# Coina

> Cryptocurrency Mobile Application

![](https://github.com/Yazan98/Coina/blob/main/resources/Screenshot%202022-12-30%20at%2010.32.59%20AM.png?raw=true)

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

## Application Flow Information

1. User Flow in The Application

![](https://github.com/Yazan98/Coina/blob/main/resources/User%20Flow.jpg?raw=true)

2. Feature Flow in Each Screen

![](https://github.com/Yazan98/Coina/blob/main/resources/Feature%20Flow.jpg?raw=true)

## Screenshots Information

1. [Application Images](https://github.com/Yazan98/Coina/tree/main/resources)
2. [Video Compare Android, IOS](https://github.com/Yazan98/Coina/blob/main/resources/Compare%20Platforms.mp4)
