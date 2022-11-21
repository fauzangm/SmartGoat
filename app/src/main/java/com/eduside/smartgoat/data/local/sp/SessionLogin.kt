package com.eduside.smartgoat.data.local.sp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.eduside.smartgoat.data.local.sp.model.FormatDataLogin
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import com.eduside.smartgoat.ui.home.MainActivity
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import splitties.activities.start
import javax.inject.Inject

class SessionLogin @Inject constructor(
    @ApplicationContext val context: Context
) {
    companion object {
        private const val PREF_NAME = "dataLoginSmartGoatCache"
        private const val DATA_LOGIIN = "dataLoginCache"
        val PREF_IS_LOGIN = "LOGIN"
        val TOKEN = "TOKEN"
        val MODETIMBANGAN = "MODE"
        val MODEFAN = "MODE"
        val MODELAMP = "MODE"
        val CURRENTPB = "CURRENTPB"
    }

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var privateMode = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME, privateMode)
        editor = pref.edit()
        editor.apply()
    }

    var dataLogin: FormatDataLogin?
        set(value) = pref.edit().putString(DATA_LOGIIN, Gson().toJson(value)).apply()
        get() {
            val data: String? = pref.getString(DATA_LOGIIN, null)
            return if (data.isNullOrBlank()) {
                null
            } else {
                Gson().fromJson(data, FormatDataLogin::class.java)
            }
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

        // Starting Login Activity
        context.start<LoginActivity>() {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}