package com.eduside.smartgoat.data.local.sp

import android.content.Context
import android.content.SharedPreferences
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataRegistrasiCache @Inject constructor(
    @ApplicationContext val _context: Context
) {
    companion object {
        private const val PREF_NAME = "dataRegistrasiBapendaWPCache"
        private const val DATA_REGISTRASI = "dataRegistrasiCache"
    }

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var privateMode = 0

    init {
        pref = _context.getSharedPreferences(PREF_NAME, privateMode)
        editor = pref.edit()
        editor.apply()
    }

    var dataRegistrasi: FormatDataRegistrasi?
        set(value) = pref.edit().putString(DATA_REGISTRASI, Gson().toJson(value)).apply()
        get() {
            val data: String? = pref.getString(DATA_REGISTRASI, null)
            return if(data.isNullOrBlank()){ null } else { Gson().fromJson(data, FormatDataRegistrasi::class.java) }
        }

}