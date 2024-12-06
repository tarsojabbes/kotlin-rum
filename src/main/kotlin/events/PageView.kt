package com.example.activityflow.src.events

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.activityflow.src.utils.Events
import com.example.activityflow.src.utils.UUID

/**
 * NavigationObserver - Custom observer to track navigation events.
 * Handles screen view/page view events and sends event payloads.
 */
class NavigationObserver private constructor(
    private val accountId: String
) : NavController.OnDestinationChangedListener {

    private var previousRoute: String? = null

    companion object {
        @Volatile
        private var instance: NavigationObserver? = null

        /**
         * Singleton accessor for NavigationObserver.
         * @param accountId Unique account identifier for tracking.
         * @return Singleton instance of NavigationObserver.
         */
        fun getInstance(accountId: String): NavigationObserver {
            return instance ?: synchronized(this) {
                instance ?: NavigationObserver(accountId).also { instance = it }
            }
        }
    }

    /**
     * Called when the navigation destination changes.
     * @param controller The NavController instance.
     * @param destination The new destination.
     * @param arguments The arguments passed to the destination (if any).
     */
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val currentRoute = destination.label?.toString() ?: "Unknown"
        trackNavigationEvent("screenView", currentRoute, previousRoute)
        previousRoute = currentRoute
    }

    /**
     * Tracks a navigation event.
     * @param eventType The type of event (e.g., "screenView").
     * @param currentRoute The current screen name.
     * @param referenceRoute The previous screen name (if any).
     */
    private fun trackNavigationEvent(
        eventType: String,
        currentRoute: String,
        referenceRoute: String?
    ) {
        val payload = mapOf(
            "eventType" to eventType,
            "accountId" to accountId,
            "currentRoute" to currentRoute,
            "referenceRoute" to (referenceRoute ?: "None")
        )
        Events.dispatch(payload)
    }
}
