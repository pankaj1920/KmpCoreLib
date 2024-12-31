package com.psbapp.datastore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform