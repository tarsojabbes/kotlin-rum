package com.example.activityflow.src.utils

object Constants {
    const val API_ENDPOINT = "https://api.example.com/events"
    const val CONTENT_TYPE = "application/json"

    enum class EventType(val type: String) {
        SCREEN_VIEW("screenView"),
        TOUCH("touch"),
        CUSTOM("custom")
    }
}
