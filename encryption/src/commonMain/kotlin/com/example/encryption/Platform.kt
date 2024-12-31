package com.example.encryption

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform