package com.example.activityflow.src.utils

import java.util.UUID

/**
 * UUID - Utility for generating unique identifiers.
 */
object UUID {

    /**
     * Generates a unique identifier.
     * @return A UUID string.
     */
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
