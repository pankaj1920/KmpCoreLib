package com.psbapp.permissions

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform