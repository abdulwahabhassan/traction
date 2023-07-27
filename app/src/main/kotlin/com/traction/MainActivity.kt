package com.traction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.traction.core.data.util.NetworkMonitor
import com.traction.core.designsystem.theme.TractionTheme
import com.traction.ui.TractionApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var networkMonitor: NetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TractionTheme {
                TractionApp(
                    networkMonitor
                )
            }
        }
    }
}


