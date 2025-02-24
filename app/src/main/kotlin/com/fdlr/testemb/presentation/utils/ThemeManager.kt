package com.fdlr.testemb.presentation.utils

import android.content.Context

internal const val MB_PREFERENCES = "MBPreferences"
internal const val DARK_THEME = "dark_theme"

fun saveThemePreference(context: Context, isDarkTheme: Boolean) {
    val sharedPrefs = context.getSharedPreferences(MB_PREFERENCES, Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean(DARK_THEME, isDarkTheme).apply()
}

fun loadThemePreference(context: Context): Boolean {
    val sharedPrefs = context.getSharedPreferences(MB_PREFERENCES, Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean(DARK_THEME, true)
}