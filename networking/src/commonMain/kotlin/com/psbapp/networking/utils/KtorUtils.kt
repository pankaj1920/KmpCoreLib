package com.psbapp.networking.utils


import com.psbapp.utils.logger.Print
import io.ktor.client.plugins.HttpRequestRetryConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.LoggingConfig
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

fun getJsonConfig(): Json {
    return Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        useAlternativeNames = true
        prettyPrint = true
    }
}


fun LoggingConfig.networkLogger() {
    level = LogLevel.ALL

    logger = object : Logger {
        override fun log(message: String) {
            Print.logAsync("AppDebug KtorHttpClient message:$message")
        }
    }
}


fun checkStatus(status: HttpStatusCode): Boolean {

    return (status == HttpStatusCode.OK || status == HttpStatusCode.Created ||
            status == HttpStatusCode.Accepted || status == HttpStatusCode.NonAuthoritativeInformation ||
            status == HttpStatusCode.NoContent || status == HttpStatusCode.ResetContent ||
            status == HttpStatusCode.PartialContent || status == HttpStatusCode.PartialContent ||
            status == HttpStatusCode.MultiStatus || status == HttpStatusCode.MultiStatus
            )

}

fun HttpRequestRetryConfig.defaultHttpRetryConfigs() {
    retryOnException(retryOnTimeout = true, maxRetries = 5)
    retryOnServerErrors(3)
    exponentialDelay()
}