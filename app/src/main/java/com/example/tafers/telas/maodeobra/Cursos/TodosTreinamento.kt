package com.example.tafers.telas.maodeobra.Cursos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.CursosRepositorio


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosTreinamentosScreen(navController: NavController) {
    val context = LocalContext.current
    val categorias = CursosRepositorio.cursos.map { it.categoria }.distinct()
    var categoriaSelecionada by remember { mutableStateOf(categorias.first()) }
    val cursosFiltrados = CursosRepositorio.cursos.filter { it.categoria == categoriaSelecionada }

    Scaffold(
        modifier = Modifier.background(Color.White),
        bottomBar = {
            com.example.tafers.componentes.NavBar(
                navController = navController,
                currentRoute = "todos_treinamentos"
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .background(Color.White)
        ) {
            Text(
                text = "Treinamentos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(categorias) { categoria ->
                    FilterChip(
                        selected = categoria == categoriaSelecionada,
                        onClick = { categoriaSelecionada = categoria },
                        label = {
                            Text(
                                text = categoria,
                                color = if (categoria == categoriaSelecionada) Color.White else Color.Black,
                                fontWeight = if (categoria == categoriaSelecionada) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = if (categoria == categoriaSelecionada) Color(0xFFFF7900) else Color.White,
                            selectedContainerColor = Color(0xFFFF7900),
                            labelColor = if (categoria == categoriaSelecionada) Color.White else Color.Black
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = if (categoria == categoriaSelecionada) Color(0xFFFF7900) else Color.Black
                        )
                    )
                }
            }
            Text("Cursos de $categoriaSelecionada", style = MaterialTheme.typography.titleMedium,)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(cursosFiltrados) { curso ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                    ) {
                        com.example.tafers.telas.ItemTreinamento(
                            curso = curso,
                            onClick = {
                                navController.navigate("detalhes/${curso.titulo}/${curso.descricao}/${curso.imagemResId}")
                            },
                            context = context,
                            modifier = Modifier
                                .matchParentSize()
                        )
                        Button(
                            onClick = {
                                navController.navigate("detalhes/${curso.titulo}/${curso.descricao}/${curso.imagemResId}")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7900)),
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp)
                        ) {
                            Text("Ver curso", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                    // Usa o mesmo card da tela inicial, com áudio

                }
            }
        }
    }
}