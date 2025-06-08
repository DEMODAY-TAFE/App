package com.example.tafers.telas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.CursosRepositorio

@Composable
fun TodosTreinamentosScreen(navController: NavController) {
    val categorias = CursosRepositorio.cursos.map { it.categoria }.distinct()
    var categoriaSelecionada by remember { mutableStateOf(categorias.first()) }
    val cursosFiltrados = CursosRepositorio.cursos.filter { it.categoria == categoriaSelecionada }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias) { categoria ->
                FilterChip(
                    selected = categoria == categoriaSelecionada,
                    onClick = { categoriaSelecionada = categoria },
                    label = { Text(categoria) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Cursos de $categoriaSelecionada", style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(cursosFiltrados) { curso ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(2.dp),
                    onClick = {
                        navController.navigate("detalhes/${curso.titulo}/${curso.descricao}/${curso.imagemResId}")
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = curso.imagemResId),
                            contentDescription = curso.titulo,
                            modifier = Modifier.size(48.dp)
                        )
                        Column {
                            Text(
                                text = curso.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = curso.descricao,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}