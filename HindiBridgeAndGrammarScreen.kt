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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.CurriculumRepository
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.data.WritingCorrection
import com.englishseekho.pro.services.FeedbackManager
import com.englishseekho.pro.services.SpeechCoach
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.ErrorRed
import com.englishseekho.pro.ui.theme.ErrorSoft
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen
import com.englishseekho.pro.ui.theme.SuccessSoft
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HindiBridgeAndGrammarScreen(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    initialSection: Int = 0,
    onSelectSection: (Int) -> Unit = {},
    onAddXp: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val section = initialSection.coerceIn(0, 4) // 0 = Traps, 1 = Hinglish, 2 = Grammar, 3 = 50 Verb Forms, 4 = Writing Coach

    var hinglishInput by remember { mutableStateOf(CurriculumRepository.sampleHinglishPresets.first()) }
    var converted by remember { mutableStateOf(CurriculumRepository.convertHinglishOffline(CurriculumRepository.sampleHinglishPresets.first())) }
    var grammarFilter by remember { mutableStateOf("सभी") }
    var verbQuery by remember { mutableStateOf("") }

    var draft by remember { mutableStateOf(CurriculumRepository.writingTemplates.last().draftText) }
    var corrections by remember { mutableStateOf<List<WritingCorrection>>(emptyList()) }
    var aiRewrite by remember { mutableStateOf("") }
    var isChecking by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "ग्रामर, Verb Forms और लेखन स्टूडियो",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "अनुवाद की गलतियों से बचें, 50 मुख्य Verb Forms (V1-V2-V3) याद करें और अपने ईमेल सुधारें।",
            style = MaterialTheme.typography.bodySmall,
            color = SlateMuted
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FilterChip(selected = section == 0, onClick = { feedback.playTap(progress.vibrationEnabled); onSelectSection(0) }, label = { Text("अनुवाद के जाल (15)") })
            FilterChip(selected = section == 1, onClick = { feedback.playTap(progress.vibrationEnabled); onSelectSection(1) }, label = { Text("Hinglish ➔ Pro") })
            FilterChip(selected = section == 2, onClick = { feedback.playTap(progress.vibrationEnabled); onSelectSection(2) }, label = { Text("Visual Grammar") })
            FilterChip(selected = section == 3, onClick = { feedback.playTap(progress.vibrationEnabled); onSelectSection(3) }, label = { Text("50 Verb Forms") })
            FilterChip(selected = section == 4, onClick = { feedback.playTap(progress.vibrationEnabled); onSelectSection(4) }, label = { Text("Writing / Email") })
        }

        when (section) {
            0 -> {
                CurriculumRepository.bridgeTraps.forEach { trap ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = CardSurface),
                        border = BorderStroke(1.dp, HairlineBorder),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("हिंदी सोच: ${trap.hindiThought}", fontWeight = FontWeight.Bold)
                            Card(colors = CardDefaults.cardColors(containerColor = ErrorSoft), modifier = Modifier.fillMaxWidth()) {
                                Text("गलत अनुवाद ❌: ${trap.wrongLiteral}", color = ErrorRed, modifier = Modifier.padding(10.dp))
                            }
                            Card(colors = CardDefaults.cardColors(containerColor = SuccessSoft), modifier = Modifier.fillMaxWidth()) {
                                Text("सही English ✅: ${trap.naturalEnglish}", color = SuccessGreen, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                            }
                            Text(trap.reasonHi, style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                            OutlinedButton(onClick = { speechCoach.speak(trap.naturalEnglish, 0.9f) }) {
                                Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text("सही वाक्य सुनें")
                            }
                        }
                    }
                }
            }

            1 -> {
                Text("नीचे कोई Hinglish वाक्य चुनें या खुद लिखें:", style = MaterialTheme.typography.labelLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    CurriculumRepository.sampleHinglishPresets.forEach { preset ->
                        OutlinedButton(onClick = {
                            hinglishInput = preset
                            converted = CurriculumRepository.convertHinglishOffline(preset)
                            feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                            onAddXp(10)
                        }) {
                            Text(preset)
                        }
                    }
                }

                OutlinedTextField(
                    value = hinglishInput,
                    onValueChange = { hinglishInput = it },
                    label = { Text("अपनी Hinglish लिखें") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (hinglishInput.isNotBlank()) {
                            converted = CurriculumRepository.convertHinglishOffline(hinglishInput)
                            feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                            onAddXp(10)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("3 स्तरों में बदलें (Simple / Formal / Smart)")
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = BorderStroke(1.dp, HairlineBorder),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("1. Simple English (आसान)", style = MaterialTheme.typography.labelLarge, color = EmeraldPrimary)
                        Text(converted.simpleEn, fontWeight = FontWeight.SemiBold)
                        OutlinedButton(onClick = { speechCoach.speak(converted.simpleEn) }) { Text("सुनें") }

                        Text("2. Formal / Office English", style = MaterialTheme.typography.labelLarge, color = EmeraldPrimary)
                        Text(converted.formalEn, fontWeight = FontWeight.SemiBold)
                        OutlinedButton(onClick = { speechCoach.speak(converted.formalEn) }) { Text("सुनें") }

                        Text("3. Smart / Natural English", style = MaterialTheme.typography.labelLarge, color = EmeraldPrimary)
                        Text(converted.smartEn, fontWeight = FontWeight.SemiBold)
                        OutlinedButton(onClick = { speechCoach.speak(converted.smartEn) }) { Text("सुनें") }

                        Text("टिप: ${converted.grammarTipHi}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                }
            }

            2 -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("सभी", "Tenses", "Prepositions", "Modals").forEach { cat ->
                        FilterChip(
                            selected = grammarFilter == cat,
                            onClick = { grammarFilter = cat },
                            label = { Text(cat) }
                        )
                    }
                }

                val filtered = CurriculumRepository.grammarConcepts.filter {
                    grammarFilter == "सभी" || it.category == grammarFilter
                }

                filtered.forEach { concept ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = CardSurface),
                        border = BorderStroke(1.dp, HairlineBorder),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("${concept.category} · ${concept.titleHi}", fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                            Card(colors = CardDefaults.cardColors(containerColor = EmeraldLight), modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(concept.timelineLabelHi, style = MaterialTheme.typography.labelMedium, color = EmeraldPrimary)
                                    Text("Formula: ${concept.formulaEn}", fontWeight = FontWeight.Bold)
                                }
                            }
                            Text("उदाहरण: ${concept.exampleEn}", fontWeight = FontWeight.SemiBold)
                            Text("अर्थ: ${concept.exampleHi}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                            Text("बिना रटे ट्रिक: ${concept.trickHi}", style = MaterialTheme.typography.bodySmall)
                            OutlinedButton(onClick = { speechCoach.speak(concept.exampleEn) }) {
                                Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text("उदाहरण सुनें")
                            }
                        }
                    }
                }
            }

            3 -> {
                OutlinedTextField(
                    value = verbQuery,
                    onValueChange = { verbQuery = it },
                    label = { Text("Verb खोजें (जैसे: Go, जाना, Eat...)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                val filteredVerbs = CurriculumRepository.verbForms.filter {
                    val q = verbQuery.trim().lowercase()
                    q.isBlank() || it.v1.lowercase().contains(q) || it.v2.lowercase().contains(q) || it.meaningHi.contains(q)
                }

                Text(
                    text = "दिखाए गए: ${filteredVerbs.size} / ${CurriculumRepository.verbForms.size} मुख्य क्रियाएं",
                    style = MaterialTheme.typography.labelMedium,
                    color = SlateMuted
                )

                filteredVerbs.forEach { v ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = CardSurface),
                        border = BorderStroke(1.dp, HairlineBorder),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                Text(
                                    text = "${v.v1}  •  ${v.v2}  •  ${v.v3}",
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldPrimary
                                )
                                Text("उच्चारण: ${v.pronunciationHi}", style = MaterialTheme.typography.bodySmall)
                                Text("हिंदी अर्थ: ${v.meaningHi}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                            }
                            OutlinedButton(onClick = {
                                speechCoach.speak("${v.v1}, ${v.v2}, ${v.v3}", 0.85f)
                            }) {
                                Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                            }
                        }
                    }
                }
            }

            else -> {
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
                        feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
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
        }
    }
}
