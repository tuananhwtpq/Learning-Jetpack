package com.example.unscramble.ui.quizz_app_mini.data

const val INCREASE_SCORE = 20
val questions = listOf<Question>(
    Question(
        id = 1,
        question = "Kotlin is mainly used for?",
        listAnswer = listOf(
            "Android", "iOS", "Symbian", "BlackBerry"
        ),
        correctAnswer = "Android"
    ),
    Question(
        id = 2,
        question = "Jetpack Compose is used to build?",
        listAnswer = listOf(
            "UI", "Database", "Network layer"
        ),
        correctAnswer = "UI"
    ),

    Question(
        id = 3,
        question = "ViewModel helps store?",
        listAnswer = listOf(
            "UI-related state", "APK files", "Internet cache only", "XML drawables"
        ),
        correctAnswer = "UI-related state"
    ),
    Question(
        id = 4,
        question = "StateFlow is a type of?",
        listAnswer = listOf(
            "Observable data holder flow", "Database table", "Network protocol", "Build tool"
        ),
        correctAnswer = "Observable data holder flow"
    ),

    Question(
        id = 5,
        question = "Composable functions are marked with?",
        listAnswer = listOf(
            "@Composable", "@Preview", "@Entity", "@Parcelize"
        ),
        correctAnswer = "@Composable"
    )


)