package com.eduside.smartgoat.data.local.sp

import android.content.Context
import android.content.SharedPreferences
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
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

}