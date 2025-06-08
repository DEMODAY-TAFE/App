package com.example.tafers.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class NavBarItem(val label: String, val icon: ImageVector, val route: String)

val navBarItems = listOf(
    NavBarItem("Home", Icons.Default.Home, "home"),
    NavBarItem("Treinamentos", Icons.Default.Lock, "todos_treinamentos"),
    NavBarItem("chat", Icons.Default.Star, "chat"),
    NavBarItem("Certificados", Icons.Default.Star, "certificate"),
    NavBarItem("Perfil", Icons.Default.Person, "perfil")
)

@Composable
fun NavBar(navController: NavController, currentRoute: String) {
    NavigationBar {
        navBarItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}