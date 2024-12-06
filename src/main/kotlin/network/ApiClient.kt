package com.example.activityflow.src.network

import com.example.activityflow.src.utils.Constants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object ApiClient {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Sends an event payload to the API endpoint.
     * @param payload The event data as a JSONObject.
     */
    fun sendEvent(payload: JSONObject) {
        val requestBody = payload.toString().toRequestBody(Constants.CONTENT_TYPE.toMediaTypeOrNull())

        val request = Request.Builder()
            .url(Constants.API_ENDPOINT)
            .post(requestBody)
            .addHeader("Content-Type", Constants.CONTENT_TYPE)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw Exception("Failed to send event: ${response.message}")
        }
    }
}
