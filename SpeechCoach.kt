package com.englishseekho.pro.services

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale

data class PronunciationResult(
    val spokenText: String,
    val matchedWords: Int,
    val totalWords: Int,
    val feedbackHi: String,
    val missedWords: List<String>
)

class SpeechCoach(private val context: Context) {
    private var tts: TextToSpeech? = null
    private var ttsReady = false
    private var recognizer: SpeechRecognizer? = null

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                configureBestUnderstandableVoice()
                ttsReady = true
            }
        }
    }

    private fun configureBestUnderstandableVoice() {
        val engine = tts ?: return
        val indianLocale = Locale("en", "IN")
        val preferredLocales = listOf(indianLocale, Locale.UK, Locale.US, Locale.ENGLISH)

        var chosenLocale: Locale = indianLocale
        for (loc in preferredLocales) {
            val res = runCatching { engine.setLanguage(loc) }.getOrDefault(TextToSpeech.LANG_NOT_SUPPORTED)
            if (res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED) {
                chosenLocale = loc
                break
            }
        }

        // Pick the clearest local/high-quality English voice so every Vivo/Android phone sounds crisp & natural
        runCatching {
            val allVoices = engine.voices.orEmpty().filter {
                it.locale?.language?.equals("en", ignoreCase = true) == true
            }
            if (allVoices.isNotEmpty()) {
                val bestVoice = allVoices.maxByOrNull { scoreVoice(it, chosenLocale) }
                if (bestVoice != null) {
                    engine.voice = bestVoice
                }
            }
        }

        // Warm, clear, human-like pitch
        runCatching { engine.setPitch(0.98f) }
    }

    private fun scoreVoice(voice: Voice, preferredLocale: Locale): Int {
        var score = 0
        val country = voice.locale?.country?.uppercase(Locale.ROOT).orEmpty()
        val name = voice.name.lowercase(Locale.ROOT)

        // Indian English is easiest for Hindi-medium learners to understand; UK/US next
        when {
            country == "IN" -> score += 120
            country == preferredLocale.country.uppercase(Locale.ROOT) -> score += 90
            country == "GB" || country == "US" -> score += 70
            else -> score += 30
        }

        // Prefer offline local voices so audio works cleanly even without internet
        if (!voice.isNetworkConnectionRequired) score += 80

        // Prefer higher-quality synthesis voices
        score += when (voice.quality) {
            Voice.QUALITY_VERY_HIGH -> 50
            Voice.QUALITY_HIGH -> 40
            Voice.QUALITY_NORMAL -> 25
            else -> 5
        }

        // Prefer clear standard voices over experimental/compressed ones
        if (name.contains("local")) score += 20
        if (name.contains("sfg") || name.contains("enc") || name.contains("ena") || name.contains("tpf")) score += 10

        return score
    }

    fun speak(text: String, rate: Float = 1.0f) {
        if (!ttsReady) return
        // Slightly calmer default pace (0.92x) makes every word crystal-clear for learners
        val clearRate = if (rate >= 0.98f) 0.92f else rate.coerceIn(0.65f, 1.15f)
        runCatching {
            tts?.setPitch(0.98f)
            tts?.setSpeechRate(clearRate)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utterance_${System.currentTimeMillis()}")
        }
    }

    fun startListening(
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            onError("इस फोन में Google Speech Service उपलब्ध नहीं है। कृपया Google ऐप अपडेट करें।")
            return
        }
        recognizer?.destroy()
        recognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {
                    onError("आवाज़ साफ़ नहीं सुनी गई (Code $error)। कृपया दोबारा माइक दबाकर धीरे और साफ़ बोलें।")
                }
                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    val best = matches?.firstOrNull().orEmpty()
                    if (best.isBlank()) {
                        onError("कोई शब्द पहचान में नहीं आया।")
                    } else {
                        onResult(best)
                    }
                }
                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }
        recognizer?.startListening(intent)
    }

    fun stopListening() {
        recognizer?.stopListening()
    }

    fun evaluatePronunciation(expected: String, spoken: String): PronunciationResult {
        val cleanExpected = expected.lowercase().replace(Regex("[^a-z0-9\\s]"), "").split(Regex("\\s+")).filter { it.isNotBlank() }
        val cleanSpoken = spoken.lowercase().replace(Regex("[^a-z0-9\\s]"), "").split(Regex("\\s+")).filter { it.isNotBlank() }.toSet()
        val missed = cleanExpected.filter { !cleanSpoken.contains(it) }
        val matched = cleanExpected.size - missed.size
        val pct = if (cleanExpected.isEmpty()) 0 else (matched * 100) / cleanExpected.size

        val feedback = when {
            pct >= 85 -> "शाबाश! बहुत साफ उच्चारण ($pct% मैच)।"
            pct >= 55 -> "अच्छा प्रयास ($pct% मैच)! छूटे हुए शब्दों को सुनकर दोबारा बोलें।"
            else -> "कोई बात नहीं ($pct% मैच)! पहले 0.75x धीमी स्पीड में सुनें, फिर दोहराएं।"
        }
        return PronunciationResult(spoken, matched, cleanExpected.size, feedback, missed)
    }

    suspend fun askFreeGeminiTutor(apiKey: String, prompt: String): Result<String> = withContext(Dispatchers.IO) {
        if (apiKey.isBlank()) {
            return@withContext Result.failure(IllegalStateException("NO_KEY"))
        }
        runCatching {
            val endpoint = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=${apiKey.trim()}")
            val conn = (endpoint.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                connectTimeout = 15000
                readTimeout = 20000
                doOutput = true
            }
            val body = JSONObject().apply {
                put("contents", JSONArray().put(
                    JSONObject().put("parts", JSONArray().put(
                        JSONObject().put("text", prompt)
                    ))
                ))
            }.toString()

            OutputStreamWriter(conn.outputStream, Charsets.UTF_8).use { it.write(body) }
            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val responseText = stream.bufferedReader().use { it.readText() }
            if (code !in 200..299) {
                throw IllegalStateException("API Error ($code)")
            }
            val json = JSONObject(responseText)
            json.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")
        }
    }

    fun release() {
        tts?.stop()
        tts?.shutdown()
        recognizer?.destroy()
    }
}
