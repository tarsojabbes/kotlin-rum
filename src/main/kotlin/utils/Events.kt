package com.example.activityflow.src.utils

import android.util.Log

/**
 * Events - Utility for handling event dispatch.
 */
object Events {

    private const val TAG = "Events"

    /**
     * Dispatches an event payload.
     * @param payload The event payload to send.
     */
    fun dispatch(payload: Map<String, String>) {
        // For now, we log the event. In production, send it to a server or analytics system.
        Log.d(TAG, "Dispatching event: $payload")
    }

    /**
     * Initializes the event system. Call during SDK initialization.
     * @param context Application context.
     */
    fun initialize(context: android.content.Context) {
        Log.d(TAG, "Event system initialized.")
    }
}
