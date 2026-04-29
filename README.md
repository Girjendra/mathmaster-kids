# 🎯 Math Practice Game (Android App)

An interactive Math Practice Android App built using Kotlin.
This app helps users improve their math skills through random questions, levels, scoring, and timed challenges.

------------------------------------------------------------

## 📱 Screenshots

[Start Screen]
images/start_screen.png

[Game Screen]
images/game_screen.png

[Game Over Screen]
images/game_over.png

------------------------------------------------------------

## 🚀 Features

- Random math questions (+, −, ×, ÷)
- Countdown timer for each question
- Level system (difficulty increases automatically)
- Score tracking
- High score saved using SharedPreferences
- Reset Score & Level buttons
- Sound effects for correct/wrong answers
- Game Over screen with final score
- Clean and kids-friendly UI

------------------------------------------------------------

## 🧠 How It Works

- Random numbers and operations are generated based on level
- User enters answer and submits
- Score increases for correct answers
- Timer resets after each question
- When time ends → Game Over screen appears

------------------------------------------------------------

## 🛠 Tech Stack

- Language: Kotlin
- IDE: Android Studio
- UI: XML (LinearLayout)
- Storage: SharedPreferences
- Media: MediaPlayer (sound effects)

------------------------------------------------------------

## 📂 Project Structure

MathPracticeGame
│
├── java/com.gir.mathmasterkids
│   ├── MainActivity.kt
│   ├── StartActivity.kt
│   └── GameOverActivity.kt
│
├── res/layout
│   ├── activity_main.xml
│   ├── activity_start.xml
│   └── activity_game_over.xml
│
└── res/raw
├── correct.mp3
└── wrong.mp3

------------------------------------------------------------

## ▶️ How to Run

# Clone the repository
git clone https://github.com/your-username/math-practice-app.git

# Open the project in Android Studio

# Build and run on emulator or physical device

------------------------------------------------------------

## 📌 Future Improvements

- Animations & transitions
- Global leaderboard
- Background music
- Progress tracking

------------------------------------------------------------

## 🙌 Author

Girjendra
Android Developer (Beginner → Growing 🚀)

------------------------------------------------------------

## ⭐ Support

If you like this project, give it a ⭐ on GitHub — it helps a lot!

------------------------------------------------------------

## 📁 Screenshots Setup

Create folder:
images/

Add files:
- start_screen.png
- game_screen.png
- game_over.png