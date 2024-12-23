package com.psbapp.navigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform