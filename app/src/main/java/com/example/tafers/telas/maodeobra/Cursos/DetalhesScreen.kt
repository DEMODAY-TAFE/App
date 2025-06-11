package com.example.tafers.telas.Cursos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            NavBar(navController = navController, currentRoute = "detalhes")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = imagemResId),
                contentDescription = titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

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
                onClick = { /* ação para iniciar o curso */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fazer Curso")
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