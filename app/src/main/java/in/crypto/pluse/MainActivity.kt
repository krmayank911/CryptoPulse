package `in`.crypto.pluse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.feature_dashboard.presentation.DashboardViewModel
import `in`.crypto.feature_dashboard.screen.DashboardScreen
import `in`.crypto.feature_dashboard.`source-fake`.FakeDashboardFeed
import `in`.crypto.pluse.ui.theme.CryptoPluseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoPluseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: DashboardViewModel = remember {  DashboardViewModel(FakeDashboardFeed()) }
                    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

                    DashboardScreen(
                        modifier = Modifier.fillMaxSize()
                            .background(color = CPThemeDark.appBackground),
                        dashboardUiState = state,
                        theme = CPThemeDark,
                        actions = {}
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoPluseTheme {
        Greeting("Android")
    }
}