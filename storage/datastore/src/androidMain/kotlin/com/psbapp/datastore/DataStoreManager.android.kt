package com.psbapp.datastore

import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun datastoreHelper(datastoreFileName: String): Module =
    module {
        single {
            createDataStore(producePath = { get<Context>().filesDir.resolve(datastoreFileName).absolutePath })
        }
    }