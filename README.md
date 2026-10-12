# Midterm AI Android Application

An Android application developed as a midterm project, featuring task management, calendar events, weather updates, music playback, and multiple Home Screen widgets.

## 🚀 Features

- **Task Management**: Create, view, update, and delete tasks using Room Database and Repository pattern.
- **Calendar & Events**: Manage schedule events and reminders.
- **Weather Information**: Weather data integration and display.
- **Music Player**: In-app music playback manager and controls.
- **Home Screen Widgets**: Custom interactive app widgets for:
  - Task Widget
  - Calendar Widget
  - Weather Widget
  - Music Player Widget
  - Clock Widget
- **Testing**: Comprehensive Unit Tests (`ExampleUnitTest`, `TaskRepositoryTest`) and Instrumented UI Tests (`TaskActivityEspressoTest`).

## 🛠️ Tech Stack

- **Language**: Kotlin
- **Architecture**: Repository Pattern & Clean Structure
- **Local Database**: Room persistence library
- **Networking**: Coroutines & Retrofit
- **UI & Widgets**: Android Views, Material Design, App Widgets (`RemoteViews`)
- **Testing**: JUnit, Espresso, AndroidX Test

## 📱 Project Structure

```
com.example.midterm_ai/
├── data/           # Local Database (Room), Entities, DAOs, Repositories, Weather API
├── music/          # Music Player Manager
├── ui/             # Activities, Adapters, Themes
└── widget/         # Home Screen App Widget Providers & RemoteViews Services
```

## ⚙️ Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/giakhiem090206-tech/Midterm-Androii.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and run the app on an Android Emulator or physical device.

---
Developed for Midterm Project.
