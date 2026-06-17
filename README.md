# Tehran Alarm - Emergency Alert System for Tehran, Iran

## Overview

Tehran Alarm is a modern, professional Android emergency alert application designed to notify residents of Tehran about air raid alerts. Built with Kotlin, Jetpack Compose, and Material Design 3, the app provides a reliable and user-friendly platform for receiving critical emergency notifications.

## Features

### Core Functionality
- **Real-time Emergency Alerts**: Receive instant notifications about air raid alerts
- **Immersive Alarm Screen**: Full-screen, high-visibility alert display with siren sound and vibration
- **Safety Instructions**: Display safety guidelines during emergencies
- **Live Status Dashboard**: View current emergency status and city information
- **User Feedback**: Rate application performance after alerts
- **Multi-city Support**: Architecture prepared for expansion to all Iranian cities

### Technical Features
- **Material Design 3**: Modern, professional UI/UX
- **Persian Language Support**: Full RTL support with Vazirmatn font
- **Dark Theme**: Optimized for nighttime use and battery efficiency
- **Offline Capability**: Local storage for settings and preferences
- **Real-time Messaging**: Firebase Cloud Messaging integration for push notifications
- **WebSocket Support**: Real-time alert streaming from backend
- **Responsive Design**: Fully responsive for phones and tablets

## Architecture

### Design Pattern: MVVM + Repository Pattern
```
Presentation Layer (UI)
    ↓
ViewModel Layer
    ↓
Repository Layer
    ↓
Data Sources (Remote & Local)
```

### Package Structure
```
app/
├── presentation/
│   ├── screens/
│   │   ├── splash/
│   │   ├── home/
│   │   ├── alarm/
│   │   └── rating/
│   ├── navigation/
│   ├── components/
│   └── viewmodels/
├── domain/
│   ├── models/
│   ├── repositories/
│   └── usecases/
├── data/
│   ├── remote/
│   │   └── services/
│   ├── local/
│   │   └── datastore/
│   └── repositories/
├── di/
│   └── modules/
├── services/
│   ├── firebase/
│   ├── websocket/
│   └── alarm/
├── utils/
│   └── helpers/
├── ui/
│   ├── theme/
│   └── components/
└── TehranAlarmApp.kt
```

## Technology Stack

### Android & UI
- **Jetpack Compose**: Declarative UI framework
- **Material Design 3**: Design system
- **Navigation Compose**: Type-safe navigation
- **Coil**: Image loading

### Architecture & DI
- **Hilt**: Dependency injection
- **MVVM**: Model-View-ViewModel pattern
- **Repository Pattern**: Data abstraction layer

### Data & Network
- **Retrofit 2**: HTTP client
- **OkHttp 3**: Network layer with logging
- **Gson**: JSON serialization
- **DataStore**: Secure local preferences

### Async & Reactive
- **Kotlin Coroutines**: Asynchronous programming
- **Flow**: Reactive data streams
- **Lifecycle-aware**: Coroutine scoping

### Real-time Communication
- **Firebase Cloud Messaging**: Push notifications
- **OkHttp WebSocket**: Real-time alert streaming

## API Integration

### Endpoints
- `GET /api/v1/alerts/status` - Current alert status
- `GET /api/v1/alerts/city/{cityId}` - City-specific alerts
- `POST /api/v1/devices/register` - Device registration

### Firebase Cloud Messaging
- Integration ready for push notifications
- Device token registration workflow
- Background message handling

## Screen Details

### 1. Splash Screen
- 3-second animation with app logo
- Fade-in effect
- Auto-navigation to Home

### 2. Home Screen
- Large "تهران آلارم" title
- Emergency status card with:
  - Current status indicator (خوب/پایدار/اضطراری)
  - Live clock (updates every second)
  - Current city (تهران)
  - Visual status indicator
- Yellow "تست آلارم" button for testing

### 3. Alarm Screen
- Full-screen immersive experience
- Deep emergency red background with pulsing animation
- Large white title "تهران آلارم"
- Yellow warning text "حمله هوایی به شهر تهران"
- Safety instructions card:
  - آرام بمانید (Stay calm)
  - از پنجره ها دور شوید (Move away from windows)
  - به نزدیکترین جای ایمن برفت (Go to nearest safe place)
  - از دستورالعمل های رسمی پیروی کنید (Follow official instructions)
- Siren sound playback
- Vibration pattern
- Wake lock to prevent screen sleep
- "قطع آلارم" button appears after 10 seconds

### 4. Rating Dialog
- 1-5 star rating system
- Optional comment field
- Submit and Cancel buttons

## UI/UX Design Principles

### Color Palette
- **Background**: #0A0A0A (Very dark gray/black)
- **Primary Red**: #D32F2F (Emergency alarm color)
- **Dark Red**: #B71C1C (Secondary emergency color)
- **Warning Yellow**: #FFC107 (Action buttons)
- **Text Primary**: #FFFFFF (White)
- **Text Secondary**: #B3B3B3 (Gray)
- **Surface**: #1E1E1E (Cards/containers)

### Typography
- **Font Family**: Vazirmatn (Persian support)
- **Display Sizes**: 36-57sp for titles
- **Headline Sizes**: 24-32sp
- **Body Text**: 12-16sp
- **Material Design 3 scales** for consistency

### Visual Design
- Rounded cards with subtle shadows
- Smooth Compose animations
- Minimal, professional aesthetic
- No flashy or childish elements
- Emergency-oriented design language

## Build & Dependencies

### Gradle Configuration
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34
- **Language**: Kotlin 1.9.10
- **Compose**: 2023.10.01 BOM

### Key Dependencies
- androidx.compose:compose-bom:2023.10.01
- androidx.navigation:navigation-compose:2.7.4
- com.google.dagger:hilt-android:2.48
- com.squareup.retrofit2:retrofit:2.10.0
- com.google.firebase:firebase-bom:32.6.0

## Installation & Setup

### Prerequisites
- Android Studio (latest)
- JDK 17+
- Android SDK 26+

### Build Steps
```bash
# Clone repository
git clone https://github.com/yourusername/tehran-alarm.git
cd tehran-alarm

# Build project
./gradlew build

# Run on device/emulator
./gradlew installDebug
```

### Firebase Setup
1. Create Firebase project at https://console.firebase.google.com
2. Add Android app configuration
3. Download google-services.json
4. Place in app/ directory

## Configuration

### API Base URL
Update in `app/src/main/java/com/tehranalaarm/app/di/Modules.kt`:
```kotlin
.baseUrl("https://api.tehran-alarm.ir/")
```

### Device Preferences
Managed via DataStore in `PreferencesManager`

## Future Enhancements

### Planned Features
- [ ] Multi-city support (all Iranian cities)
- [ ] Telegram bot integration for alerts
- [ ] Advanced notification scheduling
- [ ] User location-based alerts
- [ ] Emergency contact sharing
- [ ] Alert history and analytics
- [ ] Offline alert caching
- [ ] Voice-guided safety instructions

### Architecture Readiness
- Repository pattern supports multiple data sources
- MVVM structure scales for new features
- DI system enables easy feature toggles
- Navigation architecture supports complex flows

## Testing

### Unit Tests
Placed in `app/src/test/`

### Instrumented Tests
Placed in `app/src/androidTest/`

## Permissions Required

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## Contribution

Contributions are welcome! Please follow:
1. Create feature branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -m 'Add feature'`
3. Push branch: `git push origin feature/your-feature`
4. Open pull request

## License

MIT License - See LICENSE.md

## Support

For issues or questions:
- Create GitHub Issue
- Contact: support@tehran-alarm.ir

---

**Built with ❤️ for Tehran's Safety**
