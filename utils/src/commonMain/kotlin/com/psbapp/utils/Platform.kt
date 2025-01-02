package com.psbapp.utils

enum class PlatformType {
    IOS,
    Android
}

interface Platform {
    val type: PlatformType
}

expect fun getPlatform(): Platform