package contas.presenter.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import contas.presenter.screens.dashboard.DashboardPrincipal

@Composable
fun DashboardNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "DashboardPrincipal"){
        composable("DashboardPrincipal"){
            DashboardPrincipal()
        }
    }
}