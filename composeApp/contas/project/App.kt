package contas.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import contas.presenter.navigation.navhost.DashboardNavHost
import contas.presenter.screens.dashboard.DashboardPrincipal
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        DashboardNavHost(navController)
    }
}