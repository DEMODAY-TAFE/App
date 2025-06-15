package com.example.tafers.telas.maodeobra.Cursos

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tafers.R
import com.example.tafers.componentes.NavBar
import com.example.tafers.CursosRepositorio



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(
    titulo: String,
    descricao: String,
    imagemResId: Int,
    navController: NavController
) {
    var showVideo by remember { mutableStateOf(false) }
    val curso = CursosRepositorio.cursos.find { it.titulo == titulo }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(titulo) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavBar(navController = navController, currentRoute = "treinamentos")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!showVideo && curso?.imagemResId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable { showVideo = true }
                ) {
                    Image(
                        painter = painterResource(id = curso.imagemResId),
                        contentDescription = curso.titulo,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )
                }
            } else if (showVideo && curso?.videoUrl != null) {
                // Bloco do vídeo (YouTube ou local)
                if (curso.videoUrl.startsWith("http")) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                webViewClient = WebViewClient()
                                settings.javaScriptEnabled = true
                                val videoId = curso.videoUrl.substringAfterLast("/")
                                val embedUrl = "https://www.youtube.com/embed/$videoId"
                                loadUrl(embedUrl)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
                // ... suporte a vídeo local se necessário
            }


            Text(
                text = titulo,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = descricao,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "O que você vai aprender:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            curso?.topicos?.forEach { topico ->
                Text("• $topico", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {//vai pro quiz},
                    navController.navigate("quiz/${curso?.titulo}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Obter Certificado")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalhesScreenPreview() {
    DetalhesScreen(
        titulo = "Curso de Kotlin",
        descricao = "Aprenda os fundamentos do Kotlin, uma linguagem moderna e poderosa.",
        imagemResId = R.drawable.fundotutoriallaranja,

        navController = rememberNavController()
    )
}