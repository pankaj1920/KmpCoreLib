package com.psbapp.uidesign

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform