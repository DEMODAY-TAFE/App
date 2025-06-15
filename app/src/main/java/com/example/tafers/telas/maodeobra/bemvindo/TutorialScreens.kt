package com.example.tafers.telas.maodeobra.bemvindo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tafers.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.inc
import kotlin.text.compareTo

data class TutorialData(val imageRes: Int, val title: String, val description: String)

@Composable
fun TutorialFlow(navController: NavController) {
    val tutorialData = listOf(
        TutorialData(R.drawable.treinmento, "Treinamentos Interativos", "Descrição do tutorial 3."),
        TutorialData(R.drawable.certidicados, "Inteligencia Artificial", "Descrição do tutorial 4."),
        TutorialData(R.drawable.ia, "Certificação Digital", "Descrição do tutorial 5.")
    )

    var currentStep by remember { mutableStateOf(0) }

    LaunchedEffect(currentStep) {
        if (currentStep < tutorialData.size - 1) {
            delay(3000)
            currentStep++
        } else if (currentStep == tutorialData.size - 1) {
            delay(3000)
            navController.navigate("home") {
                popUpTo("BemVindo") { inclusive = true }
            }
        }
    }

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(
        count = tutorialData.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        TutorialScreen(
            data = tutorialData[page],
            onNext = {
                if (page < tutorialData.size - 1) {
                    scope.launch { pagerState.animateScrollToPage(page + 1) }
                } else {
                    navController.navigate("home") {
                        popUpTo("BemVindo") { inclusive = true }
                    }
                }
            },
            onSkip = {
                navController.navigate("home") {
                    popUpTo("BemVindo") { inclusive = true }
                }
            },
            currentStep = page,
            totalSteps = tutorialData.size
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialScreenPreview() {
    TutorialScreen(
        data = TutorialData(R.drawable.treinmento, "Treinamentos Interativos", "Descrição do tutorial 3"),
        onNext = {},
        onSkip = {},
        currentStep = 0,
        totalSteps = 5
    )
}

@Composable
fun TutorialScreen(
    data: TutorialData,
    onNext: () -> Unit,
    onSkip: () -> Unit,
    currentStep: Int,
    totalSteps: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = data.imageRes),
            contentDescription = data.title,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .height(400.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(230.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bolinhas de progresso
            Row {
                repeat(totalSteps) { index ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                if (index == currentStep) Color.Gray else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    if (index < totalSteps - 1) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onSkip,
                modifier = Modifier.background(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Pular",
                    tint = Color.White

                )
            }
        }
    }
}