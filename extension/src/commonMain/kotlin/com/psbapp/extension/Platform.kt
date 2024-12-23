package com.psbapp.extension

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform