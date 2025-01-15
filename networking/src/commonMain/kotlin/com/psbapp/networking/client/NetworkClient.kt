package com.psbapp.networking.client

import androidx.compose.ui.graphics.ImageBitmap
import com.psbapp.networking.response.BaseResponse
import com.psbapp.networking.utils.BaseProgressBarState
import com.psbapp.networking.utils.UploadImageRequest
import com.psbapp.networking.utils.checkStatus
import com.psbapp.utils.logger.Print
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkClient(
    val ktorClient: KtorClient
) {


    inline fun <reified T> ktorApiRequest(
        method: HttpMethod,
        apiUrl: String,
        queryParams: Map<String, String> = emptyMap(),
        body: Any? = null,
        showApiLoader: Boolean = true
    ): Flow<NetworkState<BaseResponse<T>>> = flow {
        try {
            if (showApiLoader) {
                emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
            }

            // Make the API call
            val response: HttpResponse = ktorClient.getHttpClient(true).request(apiUrl) {
                this.method = method // Set the HTTP method dynamically
                url {
                    queryParams.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }
                body?.let {
                    contentType(ContentType.Application.Json)
                    setBody(it)
                }
            }

            // Handle the response
            if (checkStatus(response.status)) {
                emit(NetworkState.Success(response.body<BaseResponse<T>>()))
            } else {
                emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
            }
            emit(NetworkState.Loading(BaseProgressBarState.Idle))
        } catch (e: Exception) {
            emit(NetworkState.Error(e.message ?: "Something went wrong"))
        }
    }


    inline fun <reified T> ktorRequest(
        crossinline requestConfig: HttpRequestBuilder.() -> Unit
    ): Flow<NetworkState<BaseResponse<T>>> = flow {
        try {
            emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))

            val response: HttpResponse = ktorClient.getHttpClient(true).request {
                requestConfig(this)
            }

            if (checkStatus(response.status)) {
                emit(NetworkState.Success(response.body<BaseResponse<T>>()))
            } else {
                emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
            }
            emit(NetworkState.Loading(BaseProgressBarState.Idle))
        } catch (e: Exception) {
            emit(NetworkState.Error(e.message ?: "Something went wrong"))
        }
    }


    inline fun <reified T> ktorGetFlowRequest(
        apiUrl: String,
        queryParams: Map<String, String> = emptyMap(),
        body: Any? = null,
        showApiLoader: Boolean = true
    ): Flow<NetworkState<BaseResponse<T>>> =
        flow {
            try {
                if (showApiLoader) {
                    emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
                }
                val response: HttpResponse = ktorClient.getHttpClient(true).get(apiUrl) {
                    url {
                        queryParams.forEach { (key, value) ->
                            parameters.append(key, value)
                        }

                        body?.let {
                            contentType(ContentType.Application.Json)
                            setBody(it)
                            Print.log("ktorGetFlowRequest => $body")
                        }
                    }
                }
                if (checkStatus(response.status)) {
                    emit(NetworkState.Success(response.body<BaseResponse<T>>()))
                } else {
                    emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
                }
                emit(NetworkState.Loading(BaseProgressBarState.Idle))
            } catch (e: Exception) {
                emit(NetworkState.Error(e.message ?: "Something went wrong"))
            }
        }

    suspend inline fun <reified T> ktorPostFlowRequest(
        body: Any,
        apiUrl: String,
        queryParams: Map<String, String> = emptyMap(),
        showApiLoader: Boolean = true,
    ): Flow<NetworkState<BaseResponse<T>>> = flow {
        try {
            if (showApiLoader) {
                emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
            }

            // Make the API call
            val response: HttpResponse = ktorClient.getHttpClient(true).post(apiUrl) {
              url {
                    queryParams.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }
                setBody(body)
            }
            // Handle the response
            if (checkStatus(response.status)) {
                emit(NetworkState.Success(response.body()))
            } else {
                emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
            }
            emit(NetworkState.Loading(BaseProgressBarState.Idle))
        } catch (e: Exception) {
            println("ktorPostFlowRequest === Exception ${e.message}")
            emit(NetworkState.Error(e.message ?: "Something went wrong"))
        }
    }


    suspend inline fun <reified T> ktorPutFlowRequest(
        body: Any,
        apiUrl: String,
        showApiLoader: Boolean = true,
    ): Flow<NetworkState<BaseResponse<T>>> = flow {
        try {
            if (showApiLoader) {
                emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
            }

            // Make the API call
            val response: HttpResponse = ktorClient.getHttpClient(true).put(apiUrl) {
                setBody(body)
            }


            // Handle the response
            if (checkStatus(response.status)) {
                emit(NetworkState.Success(response.body()))
            } else {
                emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
            }
            emit(NetworkState.Loading(BaseProgressBarState.Idle))
        } catch (e: Exception) {
            emit(NetworkState.Error(e.message ?: "Something went wrong"))
        }
    }



    @OptIn(InternalAPI::class)
    suspend inline fun <reified T> ktorUploadFileFlowRequest(
        request: UploadImageRequest, // Use UploadRequest interface
        apiUrl: String,
        noinline callback: (sentByte: Long, totalByte: Long) -> Unit
    ): Flow<NetworkState<BaseResponse<T>>> {
        return flow {
            try {
                val response: HttpResponse =
                    ktorClient.getHttpClient(true).submitFormWithBinaryData(apiUrl,
                        formData {
                            request.toFormData().forEach { (key, value) ->
                                when (value) {
                                    is ByteArray -> {
                                        append(key, value, Headers.build {
                                            append(HttpHeaders.ContentType, "image/jpeg")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"changeNameOfFile.jpg\""
                                            )
                                        })
                                    }

                                    is List<*> -> {
                                        value.filterIsInstance<ImageBitmap>()
                                            .forEachIndexed { index, image ->
                                                append("$key[$index]", image, Headers.build {
                                                    append(HttpHeaders.ContentType, "image/jpeg")
                                                    append(
                                                        HttpHeaders.ContentDisposition,
                                                        "filename=\"$key$index.jpg\""
                                                    )
                                                })
                                            }
                                    }

                                    is String -> {
                                        append(key, value)
                                    }
                                }
                            }


                        }) {
//
                        onUpload { bytesSentTotal, totalByte ->
                            if (totalByte != null) {
                                if (totalByte > 0) {
                                    Print.log("totalByte => ${totalByte} and bytesSentTotal ==> ${bytesSentTotal} ")
                                }
                            }
                        }
                    }

                if (checkStatus(response.status)) {
                    emit(NetworkState.Success(response.body()))
                } else {
                    emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
                }


            } catch (e: Exception) {
                emit(NetworkState.Error(e.message ?: "Something went wrong"))
            }
        }
    }


    suspend inline fun <reified T> ktorDeleteFlowRequest(
        body: Any?,
        apiUrl: String
    ): Flow<NetworkState<BaseResponse<T>>> = flow {
        try {
            val response: HttpResponse = ktorClient.getHttpClient(true).delete(apiUrl) {
                setBody(body)
            }
            if (checkStatus(response.status)) {
                emit(NetworkState.Success(response.body()))
            } else {
                emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
            }
        } catch (e: JsonConvertException) {
            // Specific handling for JSON serialization issues
            println("ktorPostFlowRequest === JsonConvertException: ${e.message}")
            emit(NetworkState.Error("Serialization Error: ${e.message}"))
        } catch (e: Exception) {
            // General error handling
            println("ktorPostFlowRequest === Exception: ${e.message}")
            emit(NetworkState.Error(e.message ?: "Something went wrong"))
        }
    }


    suspend inline fun <reified T> ktorPostRequest(body: Any?, apiUrl: String): BaseResponse<T> {
        return ktorClient.getHttpClient(true).post(apiUrl) {
            setBody(body)
        }.body()
    }


    suspend fun <T> ktorGetRequest(apiUrl: String): BaseResponse<T> {
        return ktorClient.getHttpClient(true).get(apiUrl).body<BaseResponse<T>>()
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T> ktorUploadFileByteArrayFlowRequest(
        picture: ByteArray? = null, // Use UploadRequest interface
        apiUrl: String,
        noinline callback: (sentByte: Long, totalByte: Long) -> Unit
    ): Flow<NetworkState<BaseResponse<T>>> {
        return flow {
            try {
                val response: HttpResponse =
                    ktorClient.getHttpClient(true).submitFormWithBinaryData(apiUrl,
                        formData {
                            appendInput(key = "picture", headers = Headers.build {
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=poolbet_image.jpeg"
                                )
                            }) {
                                buildPacket { writeFully(picture!!) }
                            }
                        }
                    )

                if (checkStatus(response.status)) {
                    emit(NetworkState.Success(response.body()))
                } else {
                    emit(NetworkState.Error(response.body<BaseResponse<String>>().message))
                }


            } catch (e: Exception) {
                emit(NetworkState.Error(e.message ?: "Something went wrong"))
            }
        }
    }

}