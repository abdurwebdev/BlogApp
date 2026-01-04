# Professional Blog App - MAD Final Project

This Android application is a comprehensive implementation of Mobile Application Development concepts, covering Assignments 1, 2, and 3 in a single, cohesive project. It features a professional UI, offline capabilities using SQLite, REST API integration using Retrofit, and robust state/theme management.

## 📱 Features

- **Authentication System**: Login, Register, Forgot Password flows with SharedPreferences for session persistence.
- **Blog Management**: 
    - Browse blogs from a public API (JSONPlaceholder).
    - Create, Read, Update, Delete (CRUD) blogs locally.
    - Offline support: Blogs are cached in a local SQLite database.
- **Themes**: Switch between Light, Dark, and Sepia (Reader Mode) themes dynamically.
- **Advanced UI**: Custom Recycler View with animations, Popups, and Context Menus.
- **WebView Integration**: Read full articles within the app without opening an external browser.
- **Robust Architecture**:
    - **MVC Pattern**: Clear separation of Models, Views (Activities), and Controllers (Adapters/Helpers).
    - **Retrofit**: For efficient networking.
    - **SQLite**: For persistent local storage.

## 🎓 Assignment Coverage

### Assignment 1 (CLO-1): Project Structure & Basics
- **Structure**: Proper package organization (`activities`, `adapters`, `models`, `network`, `database`).
- **Resources**: Custom `colors.xml`, `themes.xml`, and layouts.
- **Gradle**: Configured dependencies for Retrofit, GSON, Material Components.
- **Info Screen**: `AboutActivity` displays student details and project info.

### Assignment 2 (CLO-2): Authentication & UI Navigation
- **Screens**: Login, Register, Forgot Password, Home.
- **Navigation**: Explicit Intents used for all screen transitions.
- **Session**: `SharedPreferences` used to keep the user logged in across app restarts.
- **Validation**: Input fields have checks for empty values and password matching.

### Assignment 3 (CLO-3): Advanced Functionality
- **Theming**: `ThemeManager` utility handles dynamic theme switching.
- **Networking**: Fetches data from `https://jsonplaceholder.typicode.com/posts`.
- **Database**: `DatabaseHelper` manages `SQLiteOpenHelper` for offline storage.
- **CRUD**: Full Create/Edit/Delete capabilities for blog posts.
- **Menus**: Options Menu (Theme, Logout), Context Menu (Edit/Delete), Popup Menu.
- **WebView**: Integrated browser for external content.

## 🛠 Tech Stack

- **Language**: Java
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Libraries**:
    - Retrofit 2 & GSON Converter
    - AndroidX & Material Components
    - ConstraintLayout

## 📸 Screenshots

*(Place your screenshots here)*

## 🚀 How to Run

1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle.
4. Run on an Emulator or Real Device.
5. **Login Credentials**: 
   - Username: `admin`
   - Password: `admin123`

---
**Student Name**: [Your Name]
**Roll Number**: [Your Roll Number]
