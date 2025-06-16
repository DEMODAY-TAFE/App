// app/src/main/java/com/example/tafers/telas/maodeobra/CertificadosScreen.kt
package com.example.tafers.telas.maodeobra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.telas.maodeobra.Cursos.CertificadosConquistados

// Exemplo de dados
// app/src/main/java/com/example/tafers/telas/maodeobra/certificado.kt
val certificados = CertificadosConquistados.lista

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertificadosScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = { TopAppBar(title = { Text("Meus Certificados") }) },
        bottomBar = {com.example.tafers.componentes.NavBar(navController = navController, currentRoute = "certificados") }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(certificados) { certificado ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = certificado,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }
}
