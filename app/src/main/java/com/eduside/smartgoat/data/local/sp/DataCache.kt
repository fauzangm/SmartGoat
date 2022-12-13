package com.eduside.smartgoat.data.local.sp

import android.content.Context
import android.content.SharedPreferences
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.eduside.smartgoat.data.local.sp.model.FormatDataSwitch
import com.eduside.smartgoat.data.local.sp.model.FormatDataTimbangan
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataCache @Inject constructor(
    @ApplicationContext val _context: Context
) {
    companion object {
        private const val PREF_NAME = "dataSmarGoat"
        private const val DATA_CACHE = "dataSmartGoat"
        private const val DATA_CACHESWITCH = "dataSwitchSmartGoat"
        val IDKAMBING = ""
    }

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var privateMode = 0

    init {
        pref = _context.getSharedPreferences(PREF_NAME, privateMode)
        editor = pref.edit()
        editor.apply()
    }

    var dataTimbangan: FormatDataTimbangan?
        set(value) = pref.edit().putString(DATA_CACHE, Gson().toJson(value)).apply()
        get() {
            val data: String? = pref.getString(DATA_CACHE, null)
            return if(data.isNullOrBlank()){ null } else { Gson().fromJson(data, FormatDataTimbangan::class.java) }
        }

    var dataSwitch: FormatDataSwitch?
        set(value) = pref.edit().putString(DATA_CACHESWITCH, Gson().toJson(value)).apply()
        get() {
            val data: String? = pref.getString(DATA_CACHESWITCH, null)
            return if(data.isNullOrBlank()){ null } else { Gson().fromJson(data, FormatDataSwitch::class.java) }
        }


    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

    fun put(key: String, value: Int) {
        editor.putInt(key, value)
            .apply()
    }

    fun getInt(key: String): Int? {
        return pref.getInt(key, 0)
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }


    fun clear() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.apply()
    }
}