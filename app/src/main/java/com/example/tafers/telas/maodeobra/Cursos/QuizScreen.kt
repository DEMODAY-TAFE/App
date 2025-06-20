package com.example.tafers.telas.maodeobra.Cursos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tafers.CursosRepositorio
import com.example.tafers.componentes.NavBar

@Composable
fun QuizScreen(tituloCurso: String, navController: NavController) {
    val curso = CursosRepositorio.cursos.find { it.titulo == tituloCurso }
    val quiz = curso?.quiz ?: emptyList()
    var perguntaAtual by remember { mutableStateOf(0) }
    var respostasCorretas by remember { mutableStateOf(0) }
    var selecionada by remember { mutableStateOf(-1) }
    var finalizado by remember { mutableStateOf(false) }


    Scaffold (
        bottomBar = {
            NavBar(
                navController = navController,
                currentRoute = "certificados" // Ajuste conforme necessário
            )
        }
    ){ padding ->
        if (finalizado) {
            ResultadoFinal(
                acertos = respostasCorretas,
                total = quiz.size,
                navController = navController
            )
        } else if (quiz.isNotEmpty()) {
            val pergunta = quiz[perguntaAtual]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PerguntaHeader(perguntaAtual, quiz.size)
                PerguntaTexto(pergunta.pergunta)
                Alternativas(
                    alternativas = pergunta.alternativas,
                    selecionada = selecionada,
                    onSelecionar = { selecionada = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                BotaoProxima(
                    habilitado = selecionada != -1,
                    texto = if (perguntaAtual < quiz.size - 1) "Próxima" else "Finalizar",
                    onClick = {
                        if (selecionada == pergunta.respostaCorreta) respostasCorretas++
                        if (perguntaAtual < quiz.size - 1) {
                            perguntaAtual++
                            selecionada = -1
                        } else {
                            finalizado = true
                            curso?.titulo?.let {
                                CertificadosConquistados.adicionar(it)
                            }
                        }
                    }
                )
            }
        } else {
            Text("Nenhum quiz disponível para este curso.", modifier = Modifier.padding(32.dp))
        }
    }
}

@Composable
fun PerguntaHeader(atual: Int, total: Int) {
    Text(
        "Pergunta ${atual + 1} de $total",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun PerguntaTexto(texto: String) {
    Text(
        texto,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun Alternativas(
    alternativas: List<String>,
    selecionada: Int,
    onSelecionar: (Int) -> Unit
) {
    val azul = Color(0xFF2196F3)
    val laranja = Color(0xFFFF9800)
    alternativas.forEachIndexed { idx, alt ->
        val selecionado = selecionada == idx
        Button(
            onClick = { onSelecionar(idx) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selecionado) laranja else Color.Transparent,
                contentColor = if (selecionado) Color.White else azul
            ),
            border = BorderStroke(2.dp, if (selecionado) laranja else azul),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(alt)
        }
    }
}

@Composable
fun BotaoProxima(habilitado: Boolean, texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(texto)
    }
}

@Composable
fun ResultadoFinal(acertos: Int, total: Int, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Parabéns",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
                Text(
                    text = "Você conseguiu!",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = "Curso finalizado com ótimos resultados.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(1.dp),
                color = Color.LightGray
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Você acertou",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "$acertos",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
                Text(
                    text = "de $total do Quiz",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Button(
                onClick = { navController.navigate("certificados") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Continuar", fontSize = 18.sp)
            }
        }
    }
}