package com.psbapp.networking.datastore

interface NetworkInfo {
     fun getAccessToken(): String
     fun getBaseUrl():String
}