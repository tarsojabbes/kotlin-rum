package com.example.activityflow.src.events

import com.example.activityflow.src.session.SessionManager
import com.example.activityflow.src.utils.Constants
import org.json.JSONObject
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

object PayloadManager {

    private val lock = ReentrantLock()
    private var currentScreen: String? = null
    private var referenceScreen: String? = null

    /**
     * Updates the navigation state with the current and reference screens.
     * @param newScreen The new current screen.
     */
    fun updateNavigationState(newScreen: String) {
        lock.withLock {
            referenceScreen = currentScreen
            currentScreen = newScreen
        }
    }

    /**
     * Builds a standardized event payload.
     * @param eventType The type of event.
     * @param currentScreen The current screen.
     * @param referenceScreen The reference screen.
     * @param customData Custom event data.
     * @return A JSONObject representing the payload.
     */
    fun buildPayload(
        eventType: Constants.EventType,
        currentScreen: String,
        referenceScreen: String?,
        customData: Map<String, Any>?
    ): JSONObject {
        val payload = JSONObject()
        payload.put("eventType", eventType.type)
        payload.put("timestamp", System.currentTimeMillis())
        payload.put("currentScreen", currentScreen)
        payload.put("referenceScreen", referenceScreen ?: "None")
        payload.put("sessionId", SessionManager.getSessionId())
        payload.put("credentials", SessionManager.getCredentials())

        // Add custom data if provided
        customData?.let {
            val customDataJson = JSONObject(it)
            payload.put("customData", customDataJson)
        }

        return payload
    }
}
