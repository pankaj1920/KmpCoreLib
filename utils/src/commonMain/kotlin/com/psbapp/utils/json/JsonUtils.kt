package com.psbapp.utils.json

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer

val JSON = Json {
    explicitNulls = false
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
    isLenient = true
    useAlternativeNames = true

}

// Decode a JSON string into a specific type
inline fun <reified T> decodeFromJsonString(json: String): T? {
    return try {
        JSON.decodeFromString(json)
    } catch (e: SerializationException) {
        e.printStackTrace()
        null
    }
}

// Encode an object into a JSON string
inline fun <reified T> T.encodeToJsonString(): String? {
    return try {
        JSON.encodeToString(serializer(), this)
    } catch (e: SerializationException) {
        e.printStackTrace()
        null
    }
}

// Convert JSON string to Map<String, String>
fun String.jsonToMap(): Map<String, String> {
    return try {
        val jsonObject = JSON.decodeFromString<JsonObject>(this)
        jsonObject.mapValues { it.value.toString().trim('"') }
    } catch (e: SerializationException) {
        e.printStackTrace()
        emptyMap()
    }
}


// Convert JSON string to Map<String, JsonElement> for more flexibility
fun String.toJsonElementMap(): Map<String, JsonElement> {
    return try {
        JSON.decodeFromString<JsonObject>(this).toMap()
    } catch (e: SerializationException) {
        e.printStackTrace()
        emptyMap()
    }
}
