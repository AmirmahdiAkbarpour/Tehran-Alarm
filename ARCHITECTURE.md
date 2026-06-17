# Project Architecture Documentation

## Overview

Tehran Alarm follows a clean architecture pattern with clear separation of concerns:

```
┌─────────────────────────────────────┐
│   Presentation Layer (UI)           │
│  - Screens (Compose)                │
│  - ViewModels                       │
│  - Navigation                       │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   Domain Layer (Business Logic)     │
│  - Models                           │
│  - Repository Interfaces            │
│  - Use Cases                        │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   Data Layer (Data Sources)         │
│  - Remote (API/WebSocket)           │
│  - Local (DataStore/SharedPref)     │
│  - Repositories (Implementation)    │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   DI Layer (Dependency Injection)   │
│  - Hilt Modules                     │
└─────────────────────────────────────┘
```

## Layer Details

### Presentation Layer
- **Location**: `presentation/`
- **Components**:
  - Screens: UI implementations using Jetpack Compose
  - ViewModels: State management and business logic
  - Navigation: Screen routing and transitions
  - Components: Reusable UI components

### Domain Layer
- **Location**: `domain/`
- **Responsibility**: Core business logic
- **Independence**: No Android dependencies
- **Components**:
  - Models: Data classes (AlertStatus, Alert, Device)
  - Repositories: Abstract interfaces
  - Use Cases: Business operation orchestration

### Data Layer
- **Location**: `data/`
- **Responsibility**: Data access and management
- **Components**:
  - Remote: Retrofit services, WebSocket management
  - Local: DataStore, SharedPreferences
  - Repositories: Implementation of domain interfaces

### DI Layer
- **Location**: `di/`
- **Tool**: Hilt
- **Modules**: Organized by feature

## Data Flow

### User Action Flow
```
User Action (Button Click)
        ↓
    Screen State Update
        ↓
    ViewModel Method
        ↓
    Use Case Execution
        ↓
    Repository Call
        ↓
    Data Source (Remote/Local)
        ↓
    Result Processing
        ↓
    State Emission (Flow/StateFlow)
        ↓
    Screen Recomposition
        ↓
    UI Update
```

## State Management

### ViewModel + StateFlow Pattern
```kotlin
@HiltViewModel
class AlertViewModel @Inject constructor(
    private val repository: AlertRepository
) : ViewModel() {

    private val _alertStatus = MutableStateFlow<AlertStatus?>(null)
    val alertStatus: StateFlow<AlertStatus?> = _alertStatus.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
}
```

## Dependency Injection

### Hilt Annotations
- `@HiltViewModel`: ViewModel with DI
- `@Inject`: Constructor injection
- `@Provides`: Factory functions
- `@Singleton`: Single instance lifetime
- `@Module`: DI configuration grouping
- `@InstallIn`: Target component for module

### Module Organization
```
di/
├── Modules.kt          (Network, Local)
├── RepositoryModule.kt (Repositories)
└── FeatureModules.kt   (Feature-specific DI)
```

## Navigation Structure

### NavGraph
```kotlin
NavHost(
    navController = navController,
    startDestination = Screen.Splash.route
) {
    composable(Screen.Splash.route) { SplashScreen() }
    composable(Screen.Home.route) { HomeScreen() }
    composable(Screen.Alarm.route) { AlarmScreen() }
}
```

### Screen Routing
- Splash → Home (auto-navigate after 3s)
- Home → Alarm (on test button click)
- Alarm → Home (on stop button click)
- Rating Dialog: Overlay on Home

## Error Handling

### Try-Catch Pattern
```kotlin
try {
    _isLoading.value = true
    _error.value = null
    val data = repository.getData()
    _state.value = data
} catch (e: Exception) {
    _error.value = e.message ?: "Unknown error"
} finally {
    _isLoading.value = false
}
```

## Testing Strategy

### Unit Tests
- ViewModel logic
- Repository implementation
- Use case execution
- Utility functions

### Instrumented Tests
- UI component rendering
- Navigation flow
- Database operations
- API mocking

### Test Directory Structure
```
app/
├── src/test/          (Unit tests)
│   └── java/
│       └── com/tehranalaarm/app/
└── src/androidTest/   (Instrumented tests)
    └── java/
        └── com/tehranalaarm/app/
```

## Performance Considerations

### Memory Management
- Coroutine scoping with viewModelScope
- DisposableEffect for cleanup
- Proper resource cleanup in services

### Network Optimization
- HTTP connection pooling (OkHttp)
- Request timeouts
- Retry logic for failed requests

### UI Performance
- Efficient Compose recomposition
- LaunchedEffect for side effects
- State management optimization

## Security Best Practices

### Data Protection
- Sensitive data in DataStore (encrypted)
- HTTPS for all network calls
- Device ID generation and storage

### Code Security
- ProGuard obfuscation in release builds
- Proper permission handling
- Input validation

## Extensibility

### Adding New Features
1. Create screen in presentation layer
2. Add ViewModel if needed
3. Implement repository interface
4. Add to navigation graph
5. Inject dependencies

### Adding New Data Sources
1. Create service interface
2. Implement service class
3. Add to repository
4. Provide via Hilt module

## Future Enhancements

### Architecture Improvements
- Event bus for complex communications
- Local caching layer with Room database
- Advanced state management with MVI pattern

### Feature Expansion
- Multi-city alert system
- User preferences management
- Advanced notification scheduling
- Telegram bot integration

---

**Last Updated**: 2024
**Maintainer**: Amirmahdi Akbarpour
