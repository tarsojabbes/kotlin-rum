package com.example.activityflow.src.events

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.activityflow.src.utils.Events

/**
 * TouchTracker - A wrapper component to track touch events.
 */
class TouchTracker(
    context: Context,
    private val accountId: String,
    private val childView: View
) : ViewGroup(context) {

    private val gestureDetector = GestureDetector(context, GestureListener())
    private val screenMetrics: DisplayMetrics = context.resources.displayMetrics

    init {
        // Add the child view to this wrapper
        addView(childView)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // Pass touch events to gesture detector
        gestureDetector.onTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        // Position the child view to fill this wrapper
        childView.layout(0, 0, width, height)
    }

    /**
     * GestureListener - Handles touch gestures and dispatches events.
     */
    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            trackEvent("tap", e)
            return super.onSingleTapConfirmed(e)
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            trackEvent("doubleTap", e)
            return super.onDoubleTap(e)
        }

        override fun onLongPress(e: MotionEvent) {
            trackEvent("longPress", e)
            super.onLongPress(e)
        }

        /**
         * Tracks a touch event by sending an event payload.
         * @param gestureType The type of gesture detected (e.g., "tap").
         * @param event The motion event containing touch coordinates.
         */
        private fun trackEvent(gestureType: String, event: MotionEvent) {
            val payload = mapOf(
                "eventType" to "touch",
                "gestureType" to gestureType,
                "accountId" to accountId,
                "touchX" to event.rawX.toString(),
                "touchY" to event.rawY.toString(),
                "screenWidth" to screenMetrics.widthPixels.toString(),
                "screenHeight" to screenMetrics.heightPixels.toString()
            )
            Events.dispatch(payload)
        }
    }
}
