package com.example.github.data.local

import android.content.Context
import com.example.github.app.App
import com.example.github.utils.BooleanPreference
import com.example.github.utils.StringPreference

class LocalStorage {

    companion object {
        val pref =
            App.instance.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }

    var token by StringPreference(pref)

    var isRegister by BooleanPreference(pref)
}