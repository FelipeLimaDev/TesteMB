package com.fdlr.testemb.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fdlr.testemb.coreds.ui.theme.TesteMBTheme
import com.fdlr.testemb.navigation.AppNavigation
import com.fdlr.testemb.presentation.utils.loadThemePreference
import com.fdlr.testemb.presentation.utils.saveThemePreference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isDarkTheme = loadThemePreference(this)

        setContent {
            var darkThemeState by remember { mutableStateOf(loadThemePreference(this)) }
            TesteMBTheme(isDarkTheme = darkThemeState) {
                AppNavigation(
                    isDarkMode = darkThemeState,
                    onDarkModeChange = {
                        darkThemeState = it
                        saveThemePreference(this, it)
                    },
                    onFinished = ::finish
                )
            }
        }
    }
}