# Anime Explorer

An Android application for exploring and discovering anime series, built with modern Android development tools and best practices. The app uses the Jikan API to fetch anime data, featuring a sleek user interface, robust offline support, and a scalable architecture. It allows users to browse top anime, view detailed information, and watch trailers seamlessly.

## Features

- **Anime Discovery**: Browse a paginated list of top anime series with beautiful poster images, ratings, and episode counts.
- **Detailed Information**: View comprehensive anime details including synopsis, genres, main characters, ratings, and more.
- **Trailer Playback**: Watch anime trailers directly within the app using ExoPlayer for smooth video playback.
- **Offline Support**: All anime data is cached locally using Room database, ensuring the app works seamlessly even without internet connectivity.
- **Smart Synchronization**: Automatically syncs data with the Jikan API when online, keeping content up-to-date while maintaining offline access.
- **Network Awareness**: Real-time network monitoring ensures optimal data fetching strategies based on connectivity status.
- **Modern UI**: A responsive and intuitive user interface built with Jetpack Compose and Material Design 3.
- **Clean Architecture**: Follows MVVM architecture with a clear separation of concerns, making the codebase clean, scalable, and easy to maintain.
- **Efficient Pagination**: Uses Paging 3 library with RemoteMediator for efficient data loading and seamless infinite scrolling.
- **Dependency Injection**: Hilt is used for dependency injection, simplifying the management of dependencies and improving testability.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Asynchronous Programming**: Kotlin Coroutines & Flow for reactive data handling
- **Dependency Injection**: Hilt
- **Networking**: Retrofit with Kotlinx Serialization for consuming the Jikan REST API
- **Data Persistence**: Room database for local data storage and offline support
- **Pagination**: Paging 3 library with RemoteMediator for efficient data loading
- **Image Loading**: Coil for efficient image loading and caching
- **Video Playback**: ExoPlayer (Media3) for trailer playback
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 36

## API

The app uses the [Jikan API](https://jikan.moe/) (Unofficial MyAnimeList API) to fetch anime data:

- **Top Anime**: `https://api.jikan.moe/v4/top/anime`
- **Anime Details**: `https://api.jikan.moe/v4/anime/{anime_id}`

## Architecture

The app follows MVVM architecture with a clear separation of concerns:

```
app/src/main/java/com/example/animeexplorer/
├── data/
│   ├── repository/     # Data repository implementation
│   ├── remote/        # Retrofit API service and DTOs
│   ├── local/         # Room database, entities, and DAOs
│   ├── paging/        # RemoteMediator for pagination
│   ├── mapper/        # Data mapping utilities
│   └── network/       # Network monitoring utilities
├── domain/
│   ├── model/         # Domain models
│   └── repository/    # Domain repository interfaces
├── ui/
│   └── theme/         # Compose UI theme
├── presentation/      # ViewModels and UI components
│   ├── viewmodel/     # ViewModels
│   ├── components/    # Compose UI screens and components
│   └── navigation/    # Navigation setup
├── di/                # Dependency injection modules
└── utils/             # Utility classes and constants
```

## Key Components

### AnimeRemoteMediator
Manages the synchronization between remote API and local database for pagination:
- Fetches data from the Jikan API in pages
- Caches data in Room database for offline access
- Handles pagination state and remote keys
- Ensures data consistency during refresh and append operations

### NetworkMonitor
Monitors network connectivity in real-time:
- Provides a Flow that emits network status changes
- Helps the app make intelligent decisions about data fetching
- Ensures optimal user experience based on connectivity

### AnimeRepository
Manages all anime-related data operations:
- Fetches anime list with pagination support
- Retrieves detailed anime information
- Handles offline/online data fetching strategies
- Provides a single source of truth for anime data

### AnimeViewModel
Exposes the anime state to the UI and handles user interactions:
- Manages paginated anime list using Paging 3
- Loads anime details on demand
- Monitors network connectivity status
- Handles UI state management

### TrailerPlayer
A composable component for video playback:
- Uses ExoPlayer for smooth trailer playback
- Automatically manages player lifecycle
- Provides a responsive video player UI

## How It Works

1. **Initial Load**: When the app launches, it displays cached anime data from the local Room database (if available) for instant loading.

2. **Data Synchronization**: The `AnimeRemoteMediator` automatically fetches fresh data from the Jikan API when online and updates the local database.

3. **Pagination**: As users scroll through the anime list, the Paging 3 library automatically loads more pages, fetching from the API when online or from the database when offline.

4. **Anime Details**: When a user taps on an anime, the app checks network connectivity:
   - If online: Fetches the latest details from the API and updates the cache
   - If offline: Displays cached data from the local database

5. **Trailer Playback**: When viewing anime details, trailers are played using ExoPlayer, providing a seamless viewing experience.

6. **Offline Support**: All fetched data is automatically cached in the Room database, ensuring the app remains functional even without internet connectivity.

## Project Structure

```
anime-explorer/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/animeexplorer/
│   │   │   │   ├── data/              # Data layer
│   │   │   │   │   ├── repository/    # Repository implementations
│   │   │   │   │   ├── remote/        # API services and DTOs
│   │   │   │   │   ├── local/         # Room database and entities
│   │   │   │   │   ├── paging/        # RemoteMediator
│   │   │   │   │   ├── mapper/        # Data mappers
│   │   │   │   │   └── network/       # Network utilities
│   │   │   │   ├── domain/            # Domain layer
│   │   │   │   │   ├── model/         # Domain models
│   │   │   │   │   └── repository/    # Repository interfaces
│   │   │   │   ├── ui/                # UI layer
│   │   │   │   │   └── theme/         # Compose theme
│   │   │   │   ├── presentation/      # Presentation layer
│   │   │   │   │   ├── viewmodel/     # ViewModels
│   │   │   │   │   ├── components/    # UI screens and components
│   │   │   │   │   └── navigation/    # Navigation setup
│   │   │   │   ├── di/                # Dependency injection
│   │   │   │   └── utils/             # Utilities
│   │   │   ├── res/                   # Resources
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                      # Unit tests
│   │   └── androidTest/               # Instrumented tests
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml            # Dependency versions
├── build.gradle.kts
└── README.md
```

## Demo
* https://www.youtube.com/shorts/sCKKpsk9mEs
## APK
* https://drive.google.com/file/d/1fItN-APc7HgqARncURrbbyTMiP8G5HuJ/view?usp=sharing
