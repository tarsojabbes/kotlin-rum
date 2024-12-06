package com.example.activityflow.src.events

import com.example.activityflow.src.network.ApiClient
import com.example.activityflow.src.session.SessionManager
import com.example.activityflow.src.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object EventManager {

    private val sessionManager = SessionManager
    private val apiClient = ApiClient

    /**
     * Tracks an event by sending it to the API endpoint.
     * @param eventType The type of event (e.g., "screenView").
     * @param currentScreen The current screen name.
     * @param referenceScreen The reference screen name (if applicable).
     * @param customData Optional custom event data.
     */
    suspend fun trackEvent(
        eventType: Constants.EventType,
        currentScreen: String,
        referenceScreen: String?,
        customData: Map<String, Any>? = null
    ) {
        val payload = PayloadManager.buildPayload(
            eventType, currentScreen, referenceScreen, customData
        )

        withContext(Dispatchers.IO) {
            try {
                apiClient.sendEvent(payload)
            } catch (e: Exception) {
                // Handle error (optional: offline queuing or retry logic)
                e.printStackTrace()
            }
        }
    }
}
