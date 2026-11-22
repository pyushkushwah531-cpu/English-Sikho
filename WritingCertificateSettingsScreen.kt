package com.englishseekho.pro.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.CurriculumRepository
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.data.WritingCorrection
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
fun WritingCertificateSettingsScreen(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    onSaveSettings: (name: String, geminiKey: String) -> Unit,
    onAddXp: (Int) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var subTab by remember { mutableIntStateOf(0) } // 0 = Writing Coach, 1 = Certificate, 2 = Free Settings
    var draft by remember { mutableStateOf(CurriculumRepository.writingTemplates.last().draftText) }
    var corrections by remember { mutableStateOf<List<WritingCorrection>>(emptyList()) }
    var aiRewrite by remember { mutableStateOf("") }
    var isChecking by remember { mutableStateOf(false) }

    var nameInput by remember { mutableStateOf(progress.studentName) }
    var keyInput by remember { mutableStateOf(progress.geminiApiKey) }
    var savedMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "लेखन कोच, सर्टिफिकेट और सेटिंग्स",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = subTab == 0, onClick = { subTab = 0 }, label = { Text("Email/Writing Coach") })
            FilterChip(selected = subTab == 1, onClick = { subTab = 1 }, label = { Text("सर्टिफिकेट") })
            FilterChip(selected = subTab == 2, onClick = { subTab = 2 }, label = { Text("Free AI Key & मदद") })
        }

        when (subTab) {
            0 -> {
                Text("रेडी-मेड टेम्पलेट चुनें या खुद लिखें:", style = MaterialTheme.typography.labelLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    CurriculumRepository.writingTemplates.forEach { tpl ->
                        OutlinedButton(onClick = {
                            draft = tpl.draftText
                            corrections = emptyList()
                            aiRewrite = ""
                        }) {
                            Text(tpl.titleHi)
                        }
                    }
                }

                OutlinedTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    label = { Text("ईमेल / एप्लीकेशन / वाक्य लिखें") },
                    minLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        corrections = CurriculumRepository.analyzeWritingOffline(draft)
                        onAddXp(15)
                        if (progress.geminiApiKey.isNotBlank()) {
                            isChecking = true
                            scope.launch {
                                val res = speechCoach.askFreeGeminiTutor(
                                    progress.geminiApiKey,
                                    "Fix grammar and polish this English text for a Hindi-medium student. First show the corrected English text, then explain the fixes in 2-3 short Hindi bullets:\n\n$draft"
                                )
                                isChecking = false
                                aiRewrite = res.getOrElse { "" }
                            }
                        }
                    },
                    enabled = !isChecking && draft.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isChecking) "जाँच चल रही है..." else "गलतियाँ जांचें और सुधारें (+15 XP)")
                }

                corrections.forEach { item ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = CardSurface),
                        border = BorderStroke(1.dp, HairlineBorder),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("पहचाना गया: ${item.original}", color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.SemiBold)
                            Text("सही रूप ✅: ${item.corrected}", color = SuccessGreen, fontWeight = FontWeight.Bold)
                            Text("क्यों: ${item.reasonHi}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                        }
                    }
                }

                if (aiRewrite.isNotBlank()) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = EmeraldLight),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("AI बेहतर सुझाव:", fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                            Text(aiRewrite, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            1 -> {
                val unlocked = progress.xp >= 300
                Card(
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = BorderStroke(2.dp, EmeraldPrimary),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("CERTIFICATE OF COMPLETION", style = MaterialTheme.typography.labelLarge, color = EmeraldPrimary, fontWeight = FontWeight.Bold)
                        Text("English सीखो Pro · Hindi-to-English Foundation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("यह प्रमाणित किया जाता है कि", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                        Text(progress.studentName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                        Text("ने ${progress.level.titleHi} स्तर पर ${progress.xp} XP और ${progress.streakDays} दिन की नियमित प्रैक्टिस के साथ अपना अभ्यास जारी रखा।")
                        Text(
                            text = if (unlocked) "स्थिति: अनलॉक्ड ✅ (बधाई हो!)" else "स्थिति: ${300 - progress.xp} XP और कमाएं सर्टिफिकेट पक्का करने के लिए।",
                            color = if (unlocked) SuccessGreen else MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "मैं 'English सीखो Pro' 100% Native Android ऐप से English सीख रहा हूँ! मेरा स्कोर: ${progress.xp} XP (${progress.level.titleHi})।"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "प्रगति शेयर करें"))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("अपनी प्रगति शेयर करें")
                }
            }

            else -> {
                Card(
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = BorderStroke(1.dp, HairlineBorder),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("प्रोफ़ाइल और वैकल्पिक Free Gemini AI Key", fontWeight = FontWeight.Bold)
                        Text(
                            text = "ध्यान दें: इस ऐप के 90% फीचर्स (Flashcards, Grammar, Stories, Mic Practice, Mistake Fixer) बिना किसी API Key और बिना इंटरनेट के 100% फ्री चलते हैं। अगर आप अनलिमिटेड AI चैट भी चाहते हैं, तो Google AI Studio से बिल्कुल फ्री Key लेकर नीचे डाल सकते हैं।",
                            style = MaterialTheme.typography.bodySmall,
                            color = SlateMuted
                        )

                        OutlinedTextField(
                            value = nameInput,
                            onValueChange = { nameInput = it },
                            label = { Text("आपका नाम") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = keyInput,
                            onValueChange = { keyInput = it },
                            label = { Text("वैकल्पिक Free Gemini API Key") },
                            placeholder = { Text("AIza... (खाली भी छोड़ सकते हैं)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                onSaveSettings(nameInput.trim().ifBlank { "विद्यार्थी" }, keyInput.trim())
                                savedMsg = "सेटिंग्स सुरक्षित हो गईं!"
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("सेटिंग्स सेव करें")
                        }

                        if (savedMsg.isNotBlank()) {
                            Text(savedMsg, color = SuccessGreen, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}
