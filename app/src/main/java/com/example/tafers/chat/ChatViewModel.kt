package com.example.tafers.chat

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tafers.chat.Constants.apiKey
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch
import java.util.Locale

class ChatViewModel : ViewModel(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    fun initTTS(context: Context) {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        tts?.language = Locale("pt", "BR")
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onCleared() {
        tts?.shutdown()
        super.onCleared()
    }

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey
    )

    fun sendMessage(question: String) {
        viewModelScope.launch {
            val chat = generativeModel.startChat()

            messageList.add(
                MessageModel(
                    question,
                    "user"
                )
            )

            val response = chat.sendMessage(question)

            messageList.add(
                MessageModel(
                    response.text.toString(),
                    "model"
                )
            )
            speak(response.text.toString())
        }
    }
}