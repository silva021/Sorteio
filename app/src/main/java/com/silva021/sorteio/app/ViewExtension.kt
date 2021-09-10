package com.silva021.sorteio.app

import android.content.Context
import android.graphics.Color
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment

fun Button.disable(color: String){
    isEnabled = false
    setBackgroundColor(Color.parseColor(color))
}

fun Button.enable(color: String){
    isEnabled = true
    setBackgroundColor(Color.parseColor(color))
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsRaffleApp")

val Fragment.INITIALIZE_FLAG_KEY: Preferences.Key<Boolean> by lazy {
    booleanPreferencesKey("initialize_key")
}