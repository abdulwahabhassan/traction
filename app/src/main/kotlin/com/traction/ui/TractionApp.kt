package com.traction.ui

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.traction.R
import com.traction.core.data.util.NetworkMonitor
import com.traction.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TractionApp(
    networkMonitor: NetworkMonitor,
    appState: TractionAppState = rememberTractionAppState(
        networkMonitor = networkMonitor
    )
) {
    val isLoadingState = rememberSaveable() { mutableStateOf(true) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(150.dp, 50.dp)
                        .padding(4.dp),
                    painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_traction_logo),
                    contentDescription = stringResource(R.string.desc_traction_logo),
                    tint = Color.Unspecified
                )
                androidx.compose.animation.AnimatedVisibility(
                    visible = isLoadingState.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    ) { padding ->
        AppNavHost(
            modifier = Modifier.padding(padding),
            appNavController = appState.navHostController,
            onUpdateLoadingState = { isLoading: Boolean ->
                isLoadingState.value = isLoading
            }
        )
    }
}