package com.psbapp.roomdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform