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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.CurriculumRepository
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HindiBridgeAndGrammarScreen(
    speechCoach: SpeechCoach,
    onAddXp: (Int) -> Unit
) {
    var section by remember { mutableIntStateOf(0) } // 0 = Bridge Traps, 1 = Hinglish Converter, 2 = Visual Grammar
    var hinglishInput by remember { mutableStateOf(CurriculumRepository.sampleHinglishPresets.first()) }
    var converted by remember { mutableStateOf(CurriculumRepository.convertHinglishOffline(CurriculumRepository.sampleHinglishPresets.first())) }
    var grammarFilter by remember { mutableStateOf("सभी") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "सोचो हिंदी में, बोलो English में",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "शब्द-दर-शब्द हिंदी अनुवाद की गलतियों से बचें और बिना रटे Visual Grammar सीखें।",
            style = MaterialTheme.typography.bodySmall,
            color = SlateMuted
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = section == 0, onClick = { section = 0 }, label = { Text("अनुवाद के जाल") })
            FilterChip(selected = section == 1, onClick = { section = 1 }, label = { Text("Hinglish ➔ Pro") })
            FilterChip(selected = section == 2, onClick = { section = 2 }, label = { Text("Visual Grammar") })
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

            else -> {
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
        }
    }
}
