# Anime Explorer

An Android application that uses the Jikan API to fetch and display a list of anime series, allowing users to view details and trailers. This project was built to demonstrate proficiency in modern Android development practices. It features a sleek user interface and a robust, scalable architecture with offline support.

## Features

- **Anime List**: Fetches and displays a list of top anime from the Jikan API, showing the title, episode count, rating, and poster image for each.
- **Anime Details**: A dedicated screen for each anime, showing a video player for the trailer, synopsis, genres, main characters, and other detailed information.
- **Offline Caching**: All anime data is stored in a local Room database for offline access, ensuring the app is usable without an internet connection.
- **Automatic Syncing**: Data is automatically synchronized with the Jikan API when the device is online to provide the latest information.
- **Robust Error Handling**: Gracefully handles API, database, and network errors to provide a smooth user experience.
- **Modern UI**: A responsive and intuitive user interface built with Jetpack Compose and Material Design 3.
- **Clean Architecture**: Follows MVVM architecture with a clear separation of concerns, making the codebase clean, scalable, and easy to maintain.
- **Pagination**: Efficiently loads and displays large lists of anime using the Paging 3 library.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Asynchronous Programming**: Kotlin Coroutines & Flow/StateFlow for reactive data handling.
- **Dependency Injection**: Hilt
- **Networking**: Retrofit for consuming the Jikan REST API
- **Data Persistence**: Room for local data storage and offline support
- **Image Loading**: Coil
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 36

## API Endpoints Used

- **Top Anime**: `https://api.jikan.moe/v4/top/anime`
- **Anime Details**: `https://api.jikan.moe/v4/anime/{anime_id}`

## Assumptions

- The Jikan API (`api.jikan.moe`) is the single source of truth and is assumed to be available.
- The app is primarily designed for phone-sized devices in portrait orientation.
- The "Main Cast" requirement is fulfilled by displaying the main characters of the anime.
- If a trailer is not available from the API, the poster image is shown in its place.

## Known Limitations

- **No User Accounts**: The app does not support user profiles, personal lists (e.g., favorites, watching), or tracking progress.
- **Limited Browsing**: Currently, the app only displays the "top anime" list and does not support browsing or filtering by genre, year, etc.

## Architecture

The app follows MVVM architecture with a clear separation of concerns:

```
app/src/main/java/com/example/animeexplorer/
├── data/
│   ├── repository/     # Data repository layer
│   └── remote/         # Retrofit API service
│   └── local/          # Room database
├── domain/
│   ├── model/          # Data models
│   └── repository/     # Domain repository interfaces
├── ui/
│   └── theme/          # Compose UI theme
├── presentation/       # ViewModels
├── di/                 # Dependency injection modules
└── utils/              # Utility classes
```

## Project Structure

```
anime-explorer/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/animeexplorer/
│   │   │   │   ├── data/              # Data layer
│   │   │   │   ├── domain/            # Domain layer
│   │   │   │   ├── ui/                # UI layer
│   │   │   │   ├── presentation/      # Presentation layer (ViewModels)
│   │   │   │   ├── di/                # Dependency injection
│   │   │   │   └── utils/             # Utilities
│   │   │   ├── res/                   # Resources
│   │   │   └── AndroidManifest.xml
│   │   └── test/                      # Unit tests
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml            # Dependency versions
├── build.gradle.kts
└── README.md
```

## Demo
* https://drive.google.com/file/d/1XgOHnwJaRdriiMMnJF4KdSVSKaWLVzUx/view?usp=sharing

## APK
* https://drive.google.com/file/d/1fItN-APc7HgqARncURrbbyTMiP8G5HuJ/view?usp=sharing