package com.example.tafers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tafers.Screen.BemVindo
import com.example.tafers.chat.ChatViewModel
import com.example.tafers.telas.DetalhesScreen
import com.example.tafers.telas.TodosTreinamentosScreen
import com.example.tafers.telas.PerfilScreen
import com.example.tafers.telas.TreinamentosScreen
import com.example.tafers.telas.bemvindo.TutorialFlow
import com.example.tafers.telas.bemvindo.WelcomeScreen
import com.example.tafers.ui.theme.TafersTheme
import com.example.tafers.chat.chact.ChatScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TafersTheme {
                TafeApp()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TafeApp() {
    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = BemVindo.route,
        modifier = Modifier
    ) {
        composable(
            BemVindo.route,
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { WelcomeScreen(navController) }
        composable(
            "tutorial",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { TutorialFlow(navController) }
        composable(
            "inicio",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { TreinamentosScreen(navController) }
        composable(
            "detalhes/{titulo}/{descricao}/{imagemResId}",
            arguments = listOf(
                navArgument("titulo") { type = NavType.StringType },
                navArgument("descricao") { type = NavType.StringType },
                navArgument("imagemResId") { type = NavType.IntType }
            ),
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { backStackEntry ->
            DetalhesScreen(
                titulo = backStackEntry.arguments?.getString("titulo") ?: "",
                descricao = backStackEntry.arguments?.getString("descricao") ?: "",
                imagemResId = backStackEntry.arguments?.getInt("imagemResId") ?: 0,
                navController = navController
            )
        }
        composable(
            "perfil",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { PerfilScreen(navController) }
        composable(
            "todos_treinamentos",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) { TodosTreinamentosScreen(navController) }
        composable(
            "chat",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val chatViewModel: ChatViewModel = viewModel()
            val context = LocalContext.current
            // Inicializa o TTS apenas uma vez
            LaunchedEffect(Unit) {
                chatViewModel.initTTS(context)
            }
            ChatScreen(modifier = Modifier, viewModel = chatViewModel)
        }
    }
}