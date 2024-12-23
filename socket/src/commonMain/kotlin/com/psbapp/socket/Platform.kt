package com.psbapp.socket

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform