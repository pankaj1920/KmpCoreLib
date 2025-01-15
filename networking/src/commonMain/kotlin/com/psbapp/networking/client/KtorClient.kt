package com.psbapp.networking.client

import com.psbapp.networking.datastore.NetworkInfo
import com.psbapp.networking.utils.KtorConstants
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.CONNECT_TIMEOUT_MILLIS
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.REQUEST_TIMEOUT_MILLIS
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.SOCKET_TIMEOUT_MILLIS
import com.psbapp.networking.utils.apiHeaders
import com.psbapp.networking.utils.defaultHttpRetryConfigs
import com.psbapp.networking.utils.networkLogger
import com.psbapp.utils.json.JSON
import com.psbapp.utils.logger.Print
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpRequestRetryConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient(
    private val networkInfo: NetworkInfo,
    private val urlProtocol: URLProtocol = URLProtocol.HTTPS,
    private val refreshTokenPlugin: HttpClientConfig<*>.() -> Unit = {},
    private val jsonConfigs: Json = JSON,
    private val httpClientEngine: HttpClientEngine = getHttpClientEngine(),
    private val httpRetryConfigs: HttpRequestRetryConfig.() -> Unit = { defaultHttpRetryConfigs() },
) {
    
    fun getHttpClient(enableNetworkLogs: Boolean) = HttpClient(engine = httpClientEngine) {

        install(ContentNegotiation) {
            json(json = jsonConfigs)
        }
        install(HttpRequestRetry) {
            httpRetryConfigs()
        }
        install(DefaultRequest) {
            apply {
                url {
                    // Protocol is also configurable please reach-kmm team for change requests.
                    protocol = urlProtocol
                    host = networkInfo.getBaseUrl()
                }
                headers {
                    apiHeaders(block = {
                        if (networkInfo.getAccessToken().isEmpty().not()) {
                            header(
                                KtorConstants.AuthHeader.AUTH_HEADER_KEY,
                                KtorConstants.AuthHeader.BEARER.plus(networkInfo.getAccessToken())
                            )

                        }
                    })
                }

            }
        }
        // Auth plugin to refresh access-token
        refreshTokenPlugin()
        install(HttpTimeout) {
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
        }

        // Log HTTP request and response
        if (enableNetworkLogs) {
            install(Logging) { networkLogger() }
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                if (statusCode == 401) {
//                    tokenManager.onTriggerEvent(TokenEvent.Logout)
                }

            }

            handleResponseExceptionWithRequest { cause, request ->
                Print.log("handleResponseExceptionWithRequest : ${cause.message}")
            }


        }


        /* install(JsonFeature){
             serializer = KotlinxSerializer(json)
         }*/

    }
}