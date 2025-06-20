package com.example.tafers.telas.maodeobra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.CertificadoModel
import com.example.tafers.CursosRepositorio
import com.example.tafers.telas.maodeobra.Cursos.CertificadosConquistados
import java.io.File
import java.io.FileOutputStream
import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.compose.material.icons.filled.Star

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertificadosScreen(navController: NavController) {
    val context = LocalContext.current
    val certificadosConquistados = CertificadosConquistados.lista

    // Busca o modelo do certificado pelo título do curso
    val certificados: List<CertificadoModel> = certificadosConquistados.mapNotNull { titulo ->
        CursosRepositorio.cursos.find { it.titulo == titulo }?.certificado
    }

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = { TopAppBar(title = { Text("Meus Certificados") }) },
        bottomBar = { com.example.tafers.componentes.NavBar(navController = navController, currentRoute = "certificados") }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(certificados.size) { index ->
                val certificado = certificados[index]
                CertificadoItem(
                    titulo = certificado.titulo,
                    imagemResId = certificado.imagemResId,
                    onCompartilhar = { compartilharCertificado(context, certificado.titulo) },
                    onBaixar = { baixarCertificado(context, certificado.titulo) }
                )
            }
        }
    }
}

@Composable
fun CertificadoItem(
    titulo: String,
    imagemResId: Int,
    onCompartilhar: () -> Unit,
    onBaixar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imagemResId),
                contentDescription = "Imagem do certificado",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = titulo, style = MaterialTheme.typography.titleMedium)
            }
            IconButton(onClick = onBaixar) {
                Icon(Icons.Default.Star, contentDescription = "Baixar")
            }
            IconButton(onClick = onCompartilhar) {
                Icon(Icons.Default.Share, contentDescription = "Compartilhar")
            }
        }
    }
}

fun compartilharCertificado(context: Context, certificado: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, certificado)
    }
    context.startActivity(Intent.createChooser(intent, "Compartilhar certificado"))
}

fun baixarCertificado(context: Context, certificado: String) {
    val fileName = "certificado.txt"
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
    FileOutputStream(file).use { it.write(certificado.toByteArray()) }
    // Opcional: mostrar mensagem de sucesso ao usuário
}