package com.psbapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


val String.Companion.EMPTY: String by lazy { "" }


abstract class BaseDataStorePrefs(private val dataStore: DataStore<Preferences>) {

    private fun <T : Any> Preferences.Key<T>.get(defaultValue: T): Flow<T> {
        return dataStore.data.map {
            it[this] ?: defaultValue
        }.distinctUntilChanged()
    }

    private fun <T : Any> Preferences.Key<T>.getNullable(defaultValue: T?): Flow<T?> {
        return dataStore.data.map {
            it[this] ?: defaultValue
        }.distinctUntilChanged()
    }

    private suspend fun <T : Any> Preferences.Key<T>.set(value: T?) {
        dataStore.edit { store ->
            value?.let {
                store[this] = it
            } ?: store.remove(this)
        }
    }

    /**
     * Get non-null value as default value.
     */

    protected fun getBoolean(key: String, defaultValue: Boolean = false) =
        booleanPreferencesKey(key).get(defaultValue)

    protected fun getInt(key: String, defaultValue: Int = 0) =
        intPreferencesKey(key).get(defaultValue)

    protected fun getLong(key: String, defaultValue: Long = 0L) =
        longPreferencesKey(key).get(defaultValue)

    protected fun getDouble(key: String, defaultValue: Double = 0.0) =
        doublePreferencesKey(key).get(defaultValue)

    protected fun getFloat(key: String, defaultValue: Float = 0F) =
        floatPreferencesKey(key).get(defaultValue)

    protected fun getString(key: String, defaultValue: String = String.EMPTY) =
        stringPreferencesKey(key).get(defaultValue)

    protected fun getBooleanNullable(key: String, defaultValue: Boolean? = null) =
        booleanPreferencesKey(key).getNullable(defaultValue)

    protected fun getIntNullable(key: String, defaultValue: Int? = null) =
        intPreferencesKey(key).getNullable(defaultValue)

    protected fun getLongNullable(key: String, defaultValue: Long? = null) =
        longPreferencesKey(key).getNullable(defaultValue)

    protected fun getDoubleNullable(key: String, defaultValue: Double? = null) =
        doublePreferencesKey(key).getNullable(defaultValue)

    protected fun getFloatNullable(key: String, defaultValue: Float? = null) =
        floatPreferencesKey(key).getNullable(defaultValue)

    protected fun getStringNullable(key: String, defaultValue: String? = null) =
        stringPreferencesKey(key).getNullable(defaultValue)


    protected suspend fun setBoolean(key: String, value: Boolean?) =
        booleanPreferencesKey(key).set(value)

    protected suspend fun setInt(key: String, value: Int?) = intPreferencesKey(key).set(value)

    protected suspend fun setLong(key: String, value: Long?) = longPreferencesKey(key).set(value)

    protected suspend fun setDouble(key: String, value: Double?) =
        doublePreferencesKey(key).set(value)

    protected suspend fun setFloat(key: String, value: Float?) = floatPreferencesKey(key).set(value)

    protected suspend fun setString(key: String, value: String?) =
        stringPreferencesKey(key).set(value)

    protected suspend fun removeString(key: String) = dataStore.edit {
        it.remove(stringPreferencesKey(key))
    }


    /** Clear Datastore **/
    suspend fun clearDataStore() {
        dataStore.edit { it.clear() }
    }

}

