package com.ltdt.coffeeshop_android_native.data.services.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ltdt.coffeeshop_android_native.common.Constants.PREFERENCES_NAME
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class SharePreferencesServiceImpl @Inject constructor(
    private val context: Context
) : SharePreferencesService {


    override suspend fun saveStringKey(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getStringKey(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}