package com.challenge.bipa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.challenge.bipa.ui.App
import com.challenge.bipa.ui.rememberAppState
import com.challenge.data.util.NetworkMonitor
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val networkMonitor: NetworkMonitor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState(networkMonitor = networkMonitor)
            val isOffline by appState.isOffline.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }
            val offlineMessage = stringResource(R.string.not_connected)

            LaunchedEffect(isOffline) {
                if (isOffline) {
                    snackbarHostState.showSnackbar(
                        message = offlineMessage,
                        duration = Indefinite,
                    )
                }
            }

            App(snackbarHostState = snackbarHostState)
        }
    }
}