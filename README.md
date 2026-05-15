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

```text
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
```

---

## 📊 Modules

### 1. Furniture Catalog
Browse furniture designs with category filters and search functionality.

### 2. Cost Estimator
Calculate furniture cost based on dimensions, wood type, labour cost, and extra cost.

### 3. Saved Quotes
Save, delete, and share furniture quotations.

### 4. Portfolio
Store and showcase completed furniture work using images.

---

## 🧮 Cost Estimation Formula

```text
Volume = Length × Width × Height
Waste Volume = Volume × 1.10
Total Cost = Material Cost + Labour Cost + Extra Cost
```

---

## 🚀 How to Run the Project

1. Clone the repository
```bash
git clone https://github.com/your-username/KashtaKala.git
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the application on emulator or Android device

---

## 🔮 Future Scope

- Firebase Cloud Sync
- WhatsApp PDF Sharing
- Multi-language Support
- AI-based Suggestions
- Revenue Analytics

---

## 👨‍💻 Developer

Tarun  
B.Tech CSE

---

## 📄 License

This project is developed for academic and educational purposes.
