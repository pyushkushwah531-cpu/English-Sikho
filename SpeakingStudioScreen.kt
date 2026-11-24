package com.englishseekho.pro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.RecordVoiceOver
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.CurriculumRepository
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.services.FeedbackManager
import com.englishseekho.pro.services.PronunciationResult
import com.englishseekho.pro.services.SpeechCoach
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SpeakingStudioScreen(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    hasMicPermission: Boolean,
    onRequestMicPermission: () -> Unit,
    selectedMode: Int = 0,
    onSelectMode: (Int) -> Unit = {},
    onAddXp: (Int) -> Unit
) {
    val mode = selectedMode.coerceIn(0, 1) // 0 = Roleplay, 1 = Pronunciation & Shadowing
    var selectedScenarioIndex by remember { mutableIntStateOf(0) }
    var turnIndex by remember { mutableIntStateOf(0) }
    var userReplyInput by remember { mutableStateOf("") }
    var aiTutorFeedback by remember { mutableStateOf("") }
    var isAiLoading by remember { mutableStateOf(false) }

    var drillIndex by remember { mutableIntStateOf(0) }
    var micStatus by remember { mutableStateOf("") }
    var pronResult by remember { mutableStateOf<PronunciationResult?>(null) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "बोलने और उच्चारण स्टूडियो",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Android का मुफ्त Text-to-Speech और माइक (Speech-to-Text) इस्तेमाल करके झिझक खत्म करें।",
            style = MaterialTheme.typography.bodySmall,
            color = SlateMuted
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            FilterChip(
                selected = mode == 0,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(0)
                },
                label = { Text("Roleplay बातचीत (5)") }
            )
            FilterChip(
                selected = mode == 1,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(1)
                },
                label = { Text("Pronunciation (8)") }
            )
        }

        if (mode == 0) {
            val scenario = CurriculumRepository.roleplayScenarios[selectedScenarioIndex]
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                CurriculumRepository.roleplayScenarios.forEachIndexed { idx, item ->
                    FilterChip(
                        selected = selectedScenarioIndex == idx,
                        onClick = {
                            selectedScenarioIndex = idx
                            turnIndex = 0
                            aiTutorFeedback = ""
                            userReplyInput = ""
                        },
                        label = { Text(item.titleHi.substringBefore(" (")) }
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                border = BorderStroke(1.dp, HairlineBorder),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(scenario.titleHi, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(scenario.contextHi, style = MaterialTheme.typography.bodySmall, color = SlateMuted)

                    val activeTextEn = if (turnIndex == 0) scenario.starterLineEn else scenario.turns[(turnIndex - 1).coerceAtMost(scenario.turns.lastIndex)].englishText
                    val activeTextHi = if (turnIndex == 0) scenario.starterLineHi else scenario.turns[(turnIndex - 1).coerceAtMost(scenario.turns.lastIndex)].hindiMeaning
                    val activeHint = if (turnIndex == 0) scenario.hintHi else scenario.turns[(turnIndex - 1).coerceAtMost(scenario.turns.lastIndex)].hintHi

                    Card(
                        colors = CardDefaults.cardColors(containerColor = EmeraldLight),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(activeTextEn, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                            Text("अर्थ: $activeTextHi", style = MaterialTheme.typography.bodySmall)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(onClick = { speechCoach.speak(activeTextEn, 0.75f) }) {
                                    Text("0.75x धीमे सुनें")
                                }
                                OutlinedButton(onClick = { speechCoach.speak(activeTextEn, 1.0f) }) {
                                    Text("1.0x सुनें")
                                }
                            }
                        }
                    }

                    Text(activeHint, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)

                    val suggestions = if (turnIndex == 0) scenario.turns.firstOrNull()?.suggestedReplies.orEmpty()
                    else scenario.turns[(turnIndex - 1).coerceAtMost(scenario.turns.lastIndex)].suggestedReplies

                    suggestions.forEach { reply ->
                        OutlinedButton(
                            onClick = { userReplyInput = reply },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("सुझाव चुनें: $reply", modifier = Modifier.fillMaxWidth())
                        }
                    }

                    OutlinedTextField(
                        value = userReplyInput,
                        onValueChange = { userReplyInput = it },
                        label = { Text("अपना जवाब लिखें या माइक से बोलें") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = {
                                if (!hasMicPermission) {
                                    onRequestMicPermission()
                                } else {
                                    micStatus = "सुन रहे हैं... अब बोलें"
                                    speechCoach.startListening(
                                        onResult = { spoken ->
                                            userReplyInput = spoken
                                            micStatus = "सुना गया: $spoken"
                                        },
                                        onError = { err -> micStatus = err }
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Rounded.Mic, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("बोलें")
                        }

                        Button(
                            onClick = {
                                if (userReplyInput.isNotBlank()) {
                                    feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                    onAddXp(15)
                                    if (progress.geminiApiKey.isNotBlank()) {
                                        isAiLoading = true
                                        scope.launch {
                                            val res = speechCoach.askFreeGeminiTutor(
                                                progress.geminiApiKey,
                                                "You are a friendly English tutor for a Hindi-medium student. Situation: ${scenario.titleHi}. Student replied: \"$userReplyInput\". Give a 2-line encouraging correction in simple Hindi + improved English sentence."
                                            )
                                            isAiLoading = false
                                            aiTutorFeedback = res.getOrElse {
                                                "ऑफलाइन फीडबैक: बहुत अच्छा प्रयास! आपका वाक्य '${userReplyInput.trim()}' आगे बढ़ने के लिए तैयार है। (+15 XP)"
                                            }
                                            turnIndex = (turnIndex + 1) % (scenario.turns.size + 1)
                                        }
                                    } else {
                                        aiTutorFeedback = "बहुत अच्छा प्रयास! आपने बोला: \"${userReplyInput.trim()}\". (+15 XP)\nटिप: बड़े वाक्यों के बजाय छोटे और स्पष्ट वाक्य बोलें।"
                                        turnIndex = (turnIndex + 1) % (scenario.turns.size + 1)
                                    }
                                }
                            },
                            enabled = !isAiLoading && userReplyInput.isNotBlank(),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(if (isAiLoading) "जाँच रहा है..." else "जवाब भेजें")
                        }
                    }

                    if (micStatus.isNotBlank()) {
                        Text(micStatus, style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                    if (aiTutorFeedback.isNotBlank()) {
                        Text(aiTutorFeedback, style = MaterialTheme.typography.bodyMedium, color = SuccessGreen, fontWeight = FontWeight.Medium)
                    }
                }
            }
        } else {
            val drill = CurriculumRepository.pronunciationDrills[drillIndex]
            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                border = BorderStroke(1.dp, HairlineBorder),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "ड्रिल ${drillIndex + 1}/${CurriculumRepository.pronunciationDrills.size} · ${drill.focusTitleHi}",
                        style = MaterialTheme.typography.labelLarge,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = drill.sentenceEn,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text("बोलने का तरीका: ${drill.phoneticHi}", style = MaterialTheme.typography.bodyMedium, color = EmeraldPrimary)
                    Text("हिंदी अर्थ: ${drill.hindiMeaning}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    Text("खास ट्रिक: ${drill.commonMistakeHi}", style = MaterialTheme.typography.bodySmall)

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { speechCoach.speak(drill.sentenceEn, 0.75f) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                            Spacer(Modifier.width(4.dp))
                            Text("0.75x धीमा")
                        }
                        OutlinedButton(
                            onClick = { speechCoach.speak(drill.sentenceEn, 1.0f) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Rounded.RecordVoiceOver, contentDescription = null)
                            Spacer(Modifier.width(4.dp))
                            Text("1.0x Shadow")
                        }
                    }

                    Button(
                        onClick = {
                            if (!hasMicPermission) {
                                onRequestMicPermission()
                            } else {
                                micStatus = "माइक चालू है... ऊपर लिखा वाक्य बोलें"
                                speechCoach.startListening(
                                    onResult = { spoken ->
                                        val res = speechCoach.evaluatePronunciation(drill.sentenceEn, spoken)
                                        pronResult = res
                                        micStatus = ""
                                        val pct = if (res.totalWords == 0) 0 else (res.matchedWords * 100) / res.totalWords
                                        if (pct >= 60) {
                                            feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                            onAddXp(15)
                                        } else {
                                            feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                                            onAddXp(5)
                                        }
                                    },
                                    onError = { err ->
                                        micStatus = err
                                        feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                                    }
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Rounded.Mic, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("माइक दबाकर उच्चारण जाँचें")
                    }

                    if (micStatus.isNotBlank()) {
                        Text(micStatus, style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }

                    pronResult?.let { result ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = EmeraldLight),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("आपने बोला: \"${result.spokenText}\"", fontWeight = FontWeight.SemiBold)
                                Text(result.feedbackHi, color = EmeraldPrimary, fontWeight = FontWeight.Bold)
                                if (result.missedWords.isNotEmpty()) {
                                    Text("इन शब्दों का अभ्यास फिर से करें: ${result.missedWords.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = {
                                drillIndex = (drillIndex - 1 + CurriculumRepository.pronunciationDrills.size) % CurriculumRepository.pronunciationDrills.size
                                pronResult = null
                            }
                        ) { Text("पिछला वाक्य") }
                        OutlinedButton(
                            onClick = {
                                drillIndex = (drillIndex + 1) % CurriculumRepository.pronunciationDrills.size
                                pronResult = null
                            }
                        ) { Text("अगला वाक्य") }
                    }
                }
            }
        }
    }
}
