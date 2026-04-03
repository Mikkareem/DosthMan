# DosthMan (ApiClientK) 🚀
A Cross-Platform REST API Client built with Kotlin and Compose Multiplatform

ApiClientK is a modern, cross-platform REST API client, similar to Postman, built using Kotlin and Compose Multiplatform. It allows developers to create, execute, and inspect HTTP requests and responses through a clean, declarative UI while sharing core logic across platforms.

The project focuses on developer productivity, clean architecture, and consistent behavior across Android, iOS, and Desktop.

---

## ✨ Features

- Create and execute HTTP requests (GET, POST, PUT, PATCH, DELETE)
- Configure headers, query parameters, and request bodies
- Inspect response status codes, headers, and payloads
- Clean and responsive UI built with Compose Multiplatform
- Shared networking and domain logic across platforms
- Modular, scalable, and testable architecture

---

## 🧱 Architecture

ApiClientK follows Clean Architecture principles to ensure maintainability and clear separation of concerns.

**Layers:**
- UI Layer: Compose Multiplatform UI
- Domain Layer: Business logic and models
- Data Layer: Networking and data sources

**Design Goals:**
- Maximum code reuse across platforms
- Easy unit testing and extensibility
- Clear boundaries between UI and networking logic

---

## 🛠️ Tech Stack

### Core
- Kotlin
- Compose Multiplatform (Android, iOS, JVM/Desktop)

### Networking
- Ktor Client
- Support for multiple HTTP methods
- Dynamic headers, query parameters, and request bodies

### Dependency Injection
- Koin
- Modular and test-friendly DI setup

### Tooling
- Gradle (Kotlin Multiplatform)
- Modular project structure

---

## 📱 Supported Platforms

- Android
- iOS
- JVM / Desktop

Business logic and networking layers are shared across all platforms.

---

## 🎯 Motivation

ApiClientK was built to explore Compose Multiplatform in a real-world developer tool, design a Postman-like workflow using a declarative UI, demonstrate clean architecture with Ktor networking and Koin dependency injection, and serve as a reference project for Kotlin Multiplatform application design.

---

## 🧠 Key Learnings

- Designing reusable UI with Compose Multiplatform
- Building flexible networking layers using Ktor Client
- Structuring Kotlin Multiplatform projects for scalability
- Sharing logic across Android, iOS, and Desktop targets

---

## 📄 License

This project is licensed under the MIT License. You are free to use, modify, and learn from it.