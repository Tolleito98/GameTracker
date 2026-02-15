# GameTracker

GameTracker is an app to track video game details and achievements.

## Tech Stack
- Kotlin
- Jetpack Compose
- Hilt (DI)
- Coil (image loading)

## Development Workflow
- Each feature is developed in its own branch.
- Commits are not squashed when merging to `main` to show the development flow.

## Current Features 
- Library screen to list games.
- Game detail screen with a list of achievements.
- Reactive checkboxes to mark achievements as completed.
- *(All using mocked data)*

## How to Run
1. Clone the repository.
2. Open in Android Studio.
3. Add to local.properties *RAWG_API_KEY* and your key 
3. Run on an emulator or a physical device.

## Future Improvements
- Navigation
- game search
- Save game and achievement progress
