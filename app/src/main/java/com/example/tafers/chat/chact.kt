package com.example.tafers.chat.chact


import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tafers.chat.ChatViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tafers.chat.MessageModel
import com.example.tafers.ui.theme.ColorUserMessage
import androidx.compose.runtime.remember as remeber



@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppHeader()
        MessageList(
            messageList = viewModel.messageList,
            viewModel = viewModel,
            modifier = Modifier.weight(1f)
        )
        MessageInput(onMessageSend = {
            viewModel.sendMessage(it)
        })
    }
}

@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    messageList: List<MessageModel>
) {
    LazyColumn (
        modifier = modifier,
        reverseLayout = true
    ){
        items(messageList.reversed()) {
            MessageItem(
                message = it,
                viewModel = viewModel,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MessageItem(
    message: MessageModel,
    viewModel: ChatViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR")
    }

    fun startListening() {
        speechRecognizer.startListening(intent)
    }

    val isModel = message.role == "model"

    if (isModel) {
        androidx.compose.runtime.LaunchedEffect(message.message) {
            viewModel.speak(message.message)
        }
    }

    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
        ){
            Box(
                modifier = Modifier.align(if (isModel) Alignment.CenterStart else Alignment.CenterEnd)
                    .background(if (isModel) ColorUserMessage else Color.LightGray)
                    .padding(
                        start = if (isModel) 16.dp else 0.dp,
                        end = if (isModel) 0.dp else 16.dp,
                    )
            ){
                Text(
                    text = message.message,
                    color = if (message.role == "user") Color.Black else Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun AppHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        Text(
            text = "Chat",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MessageInput(
    onMessageSend : (String) -> Unit,
) {
    var message by remeber { mutableStateOf("") }
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    val intent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR")
        }
    }

    val recognitionListener = object : RecognitionListener {
        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                message = matches[0]
            }
        }
        // Implemente outros métodos vazios...
        override fun onReadyForSpeech(params: Bundle?) {}
        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() {}
        override fun onError(error: Int) {}
        override fun onPartialResults(partialResults: Bundle?) {}
        override fun onEvent(eventType: Int, params: Bundle?) {}
    }
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text("Digite sua mensagem") }
        )
        IconButton(onClick = {
            speechRecognizer.startListening(intent)
        }) {
            Icon(imageVector = Icons.Default.Call, contentDescription = "Falar mensagem")
        }
        IconButton(
            onClick = {
                onMessageSend(message)
                message = ""
        }){// Limpa}) o campo de entrada após enviar a mensagem{
            Icon(
                imageVector = Icons.Default.Send, contentDescription = "Enviar mensagem")
        }
    }
}





