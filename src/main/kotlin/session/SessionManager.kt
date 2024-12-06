package com.example.activityflow.src.session

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import java.util.UUID

object SessionManager {

    private val sessionId: AtomicReference<String> = AtomicReference(UUID.randomUUID().toString())
    private val firstEvent: AtomicBoolean = AtomicBoolean(true)

    /**
     * Returns the current session ID.
     */
    fun getSessionId(): String {
        return sessionId.get()
    }

    /**
     * Renews the session ID.
     */
    fun renewSession() {
        sessionId.set(UUID.randomUUID().toString())
        firstEvent.set(true)
    }

    /**
     * Returns credential information (example implementation).
     */
    fun getCredentials(): Map<String, String> {
        return mapOf("apiKey" to "your_api_key", "userId" to "user_12345")
    }

    /**
     * Marks the first event as sent.
     */
    fun markFirstEventSent() {
        firstEvent.set(false)
    }
}
