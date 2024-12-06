package com.example.activityflow

import com.example.activityflow.src.events.PageView
import com.example.activityflow.src.events.Touch
import com.example.activityflow.src.utils.Cookie
import com.example.activityflow.src.utils.Events
import com.example.activityflow.src.utils.UUID

/**
 * ActivityFlow - Main entry point for the activity tracking SDK.
 * Provides a unified interface for tracking navigation and user interactions.
 */
object ActivityFlow {

    private val pageViewTracker = PageView()
    private val touchTracker = Touch()
    private val sessionManager = Cookie()
    private val eventDispatcher = Events()
    private val idGenerator = UUID()

    /**
     * Initializes the SDK and sets up required configurations.
     * @param context Application context for initialization.
     */
    fun initialize(context: android.content.Context) {
        sessionManager.initializeSession(context)
        eventDispatcher.initialize(context)
        log("SDK initialized successfully.")
    }

    /**
     * Tracks a page view event.
     * @param pageName The name of the page being viewed.
     */
    fun trackPageView(pageName: String) {
        val eventId = idGenerator.generate()
        pageViewTracker.track(pageName, eventId)
        log("Page view tracked: $pageName with eventId: $eventId")
    }

    /**
     * Tracks a user interaction event.
     * @param interactionType The type of interaction (e.g., "button_click").
     * @param details Additional details about the interaction.
     */
    fun trackInteraction(interactionType: String, details: Map<String, String>) {
        val eventId = idGenerator.generate()
        touchTracker.track(interactionType, details, eventId)
        log("Interaction tracked: $interactionType with eventId: $eventId")
    }

    /**
     * Logs messages for debugging purposes.
     * @param message The message to log.
     */
    private fun log(message: String) {
        // Unified logging approach
        android.util.Log.d("ActivityFlow", message)
    }
}
