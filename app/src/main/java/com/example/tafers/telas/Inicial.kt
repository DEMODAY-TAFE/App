package com.example.tafers.telas

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tafers.R
import com.example.tafers.componentes.NavBar
import java.util.Locale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.tafers.CursoRepositorio
import com.example.tafers.CursosRepositorio

// Substitua listaTreinamentos por:
val listaTreinamentos = CursosRepositorio.cursos.map {
    CursoRepositorio(
        titulo = it.titulo,
        imagemResId = it.imagemResId,
        descricao = it.descricao,
        categoria = it.categoria,
        topicos = it.topicos,
        videoUrl = it.videoUrl,
        quiz = it.quiz
    )
}


@Composable
fun ItemTreinamento(
    curso: CursoRepositorio,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .height(200.dp),
    context: Context
) {
    var tts: TextToSpeech? = remember { null }

    DisposableEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.getDefault()
            }
        }
        onDispose {
            tts?.shutdown()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
           Box {
                Image(
                    painter = painterResource(id = curso.imagemResId),
                    contentDescription = curso.titulo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentScale = ContentScale.Crop
                )
                // Ícone de play centralizado
                IconButton(
                    onClick = { /* ação do play */ },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow, // Use o ícone de play padrão
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
                // Botão de áudio no canto superior direito (mantido)
                IconButton(
                    onClick = { /* ação do áudio */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(1.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ad),
                        contentDescription = "Audio Descrição",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = curso.titulo,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = curso.descricao.split(".").firstOrNull()?.plus(".") ?: "",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "0%",
                        fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LinearProgressIndicator(
                        progress = 0.0f,
                        modifier = Modifier.fillMaxWidth().height(8.dp).background(Color.Transparent),
                        color = Color.LightGray,
                    )
                }
            }
        }
    }
}

@Composable
fun ItemDisponivel(
    curso: CursoRepositorio,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .height(200.dp),
    context: Context
) {
    var tts: TextToSpeech? = remember { null }

    DisposableEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.getDefault()
            }
        }
        onDispose {
            tts?.shutdown()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Image(
                painter = painterResource(id = curso.imagemResId),
                contentDescription = curso.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = curso.titulo,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = curso.descricao.split(".").firstOrNull()?.plus(".") ?: "",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreinamentosScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavBar(navController = navController, currentRoute = "home")
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Olá Carlos!",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
              Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                  border = BorderStroke(1.dp, Color(0xFFFF7900))
              ) {
                  Text(
                        text = "Favortitos",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFFF7900)
                  )
              }
                Button(
                onClick = {  },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color(0xFFFF7900))
              ) {
                  Text(
                        text = "Novidades",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFFF7900)
                  )
              }
                Button(
                onClick = {  },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color(0xFFFF7900))
              ) {
                  Text(
                        text = "Offline",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFFF7900)
                  )
              }
            }
            Text(
                text = "Treinamentos em Andamento",
                fontSize = 15.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(listaTreinamentos) { treinamento ->
                    ItemTreinamento(
                        curso = treinamento,
                        onClick = {
                            navController.navigate("detalhes/${treinamento.titulo}/${treinamento.descricao}/${treinamento.imagemResId}") {
                                popUpTo("treinamentos") {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        context = context,
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .padding(end = 16.dp)
                    )

                }
            }
            Column(

            ) {
                Text(
                    text = "Cursos disponíveis",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
                LazyRow (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    items(listaTreinamentos) { curso ->
                        ItemDisponivel(
                            curso = curso,
                            onClick = {
                                navController.navigate("treinamentos")

                            },
                            context = context,
                            modifier = Modifier
                                .width(300.dp)
                                .height(300.dp)
                                .padding(end = 16.dp)
                        )
                    }
                }
            }
        }

    }
}


