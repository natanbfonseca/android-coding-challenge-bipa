package com.challenge.bipa.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.challenge.common.allZero
import com.challenge.designsystem.theme.BipaTheme
import com.challenge.home.ui.HomeScreen

@Composable
fun App(snackbarHostState: SnackbarHostState) {
    BipaTheme {
        Scaffold(
            contentWindowInsets = WindowInsets.allZero(),
            snackbarHost = {
                SnackbarHost(
                    snackbarHostState,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom))
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            HomeScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            )
        }
    }
}