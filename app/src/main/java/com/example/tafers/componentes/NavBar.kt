package com.example.tafers.componentes

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.R

data class NavBarItem(val label: String, val painter: Painter, val route: String)


@Composable
fun NavBar(navController: NavController, currentRoute: String) {
    val navBarItems = listOf(
        NavBarItem("", painter = painterResource(id = R.drawable.iconhome), "home"),
        NavBarItem("", painter = painterResource(id = R.drawable.icontreinamento), "todos_treinamentos"),
        NavBarItem("", painter = painterResource(id = R.drawable.iaicon), "chat"),
        NavBarItem("", painter = painterResource(id = R.drawable.certificateicon), "certificados"),
        NavBarItem("", painter = painterResource(id = R.drawable.homeicon), "perfil")
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp,
        modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(8.dp) /* Ajuste a largura e o padding conforme necessário */
    ) {
        navBarItems.forEach { item ->
            val selected = currentRoute == item.route
            val iconColor by animateColorAsState(
                targetValue = if (selected) Color(0xFF003366) /* azul escuro */ else Color(0xFFFC6600) /* laranja */
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        item.painter,
                        contentDescription = item.label,
                        modifier = androidx.compose.ui.Modifier.size(if (selected) 32.dp else 24.dp),
                        tint = iconColor
                    )
                },
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    val navController = NavController(LocalContext.current)
    NavBar(navController, "home")
}