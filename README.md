# KashtaKala 🪵
A Smart Android App for Furniture Craftsmen

## 📌 Project Overview
KashtaKala is an Android application designed for furniture craftsmen and carpenters to digitally manage their work. The app helps users estimate furniture costs, save quotations, browse furniture catalogs, and maintain a portfolio of completed work.

The application is fully offline and built using Kotlin with Room Database following MVVM architecture.

---

## ✨ Features

- 📚 Furniture Catalog
- 🔍 Search & Filter Furniture Designs
- ❤️ Favourite Designs
- 🧮 Cost Estimator
- 💾 Save Quotes
- 📤 Share Quotes
- 📷 Portfolio Management
- 🌿 Indian Currency Formatting
- 📱 Fully Offline Functionality

---

## 🛠️ Technologies Used

- Kotlin
- XML
- MVVM Architecture
- Room Database
- SQLite
- RecyclerView
- Glide
- Navigation Component
- Kotlin Coroutines
- LiveData
- ViewBinding

---

## 🏗️ Project Architecture

UI (Fragments)  
↓  
ViewModel  
↓  
Repository  
↓  
Room Database

---

## 📂 Project Structure

``` id="2jv0qv"
com.kashta.kala/
├── data/
│   ├── model/
│   ├── dao/
│   └── repository/
├── ui/
│   ├── catalog/
│   ├── estimator/
│   ├── quotes/
│   └── portfolio/
├── utils/
├── MainActivity.kt
├── SplashActivity.kt
└── KashtaApp.kt
