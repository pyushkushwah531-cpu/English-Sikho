package com.englishseekho.pro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.CurriculumRepository
import com.englishseekho.pro.data.StudentLevel
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.ui.theme.AmberAccent
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen

@Composable
fun LevelTestScreen(
    initialName: String,
    onComplete: (name: String, level: StudentLevel, bonusXp: Int) -> Unit
) {
    val questions = CurriculumRepository.levelTestQuestions
    var name by remember { mutableStateOf(if (initialName == "विद्यार्थी") "" else initialName) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var showFeedback by remember { mutableStateOf(false) }

    val q = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "English सीखो Pro · शुरुआती लेवल जांच",
            style = MaterialTheme.typography.labelLarge,
            color = EmeraldPrimary,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "2 मिनट में जानें आपका सही स्तर",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "बिना किसी डर के 5 आसान सवालों के जवाब दें — ऐप आपके हिसाब से प्लान सेट कर देगा।",
            style = MaterialTheme.typography.bodyMedium,
            color = SlateMuted
        )

        if (currentIndex == 0 && !showFeedback) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("आपका नाम (सर्टिफिकेट के लिए)") },
                placeholder = { Text("जैसे: राहुल कुमार") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        LinearProgressIndicator(
            progress = { (currentIndex + 1).toFloat() / questions.size.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = EmeraldPrimary
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = BorderStroke(1.dp, HairlineBorder),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "प्रश्न ${currentIndex + 1} / ${questions.size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = SlateMuted
                )
                Text(
                    text = q.promptHi,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = q.sentenceEn,
                    style = MaterialTheme.typography.titleMedium,
                    color = EmeraldPrimary,
                    fontWeight = FontWeight.Bold
                )

                q.options.forEachIndexed { idx, opt ->
                    val isSelected = selectedOption == idx
                    val isCorrect = idx == q.correctIndex
                    val borderColor = when {
                        showFeedback && isCorrect -> SuccessGreen
                        showFeedback && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                        isSelected -> EmeraldPrimary
                        else -> HairlineBorder
                    }
                    OutlinedButton(
                        onClick = {
                            if (!showFeedback) {
                                selectedOption = idx
                                showFeedback = true
                                if (idx == q.correctIndex) score += 1
                            }
                        },
                        border = BorderStroke(1.5.dp, borderColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = opt,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                if (showFeedback) {
                    Text(
                        text = if (selectedOption == q.correctIndex) "सही जवाब! ${q.explanationHi}" else "समझिए: ${q.explanationHi}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedOption == q.correctIndex) SuccessGreen else AmberAccent,
                        fontWeight = FontWeight.Medium
                    )

                    Button(
                        onClick = {
                            if (currentIndex < questions.lastIndex) {
                                currentIndex += 1
                                selectedOption = -1
                                showFeedback = false
                            } else {
                                val finalLevel = when {
                                    score >= 4 -> StudentLevel.PRO
                                    score >= 2 -> StudentLevel.INTERMEDIATE
                                    else -> StudentLevel.ZERO
                                }
                                val cleanName = name.trim().ifBlank { "विद्यार्थी" }
                                onComplete(cleanName, finalLevel, 50 + score * 10)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (currentIndex < questions.lastIndex) "अगला प्रश्न" else "अपना प्लान शुरू करें")
                    }
                }
            }
        }

        TextButton(
            onClick = {
                val cleanName = name.trim().ifBlank { "विद्यार्थी" }
                onComplete(cleanName, StudentLevel.ZERO, 20)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("सीधे Zero से शुरू करें (Skip Test)")
        }
    }
}

@Composable
fun HomeDashboardScreen(
    progress: UserProgress,
    onToggleTask: (String, Int) -> Unit,
    onNavigateTab: (Int) -> Unit,
    onRetakeLevelTest: () -> Unit
) {
    val tasks = CurriculumRepository.dailyTasks
    val completedCount = tasks.count { progress.completedTaskIds.contains(it.id) }
    val certProgress = (progress.xp.coerceAtMost(300)).toFloat() / 300f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = EmeraldPrimary),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "नमस्ते, ${progress.studentName}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${progress.level.titleHi} · ${progress.level.descriptionHi}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmeraldLight
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = null,
                            tint = EmeraldLight
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "${progress.streakDays} दिन Streak",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.EmojiEvents,
                            contentDescription = null,
                            tint = EmeraldLight
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "${progress.xp} XP",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "डिजिटल सर्टिफिकेट प्रगति",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${progress.xp.coerceAtMost(300)} / 300 XP",
                        style = MaterialTheme.typography.labelMedium,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                LinearProgressIndicator(
                    progress = { certProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = EmeraldPrimary
                )
                Text(
                    text = if (progress.xp >= 300) "बधाई हो! आपका सर्टिफिकेट 'लिखें/अधिक' टैब में तैयार है।" else "रोज़ के 10 मिनट पूरे करके सर्टिफिकेट अनलॉक करें।",
                    style = MaterialTheme.typography.bodySmall,
                    color = SlateMuted
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "आज का 10-मिनट प्लान ($completedCount/${tasks.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = onRetakeLevelTest) {
                Text("लेवल जांच फिर से")
            }
        }

        tasks.forEach { task ->
            val done = progress.completedTaskIds.contains(task.id)
            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                border = BorderStroke(1.dp, if (done) SuccessGreen else HairlineBorder),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleTask(task.id, task.xpReward) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = if (done) Icons.Rounded.CheckCircle else Icons.Rounded.RadioButtonUnchecked,
                            contentDescription = null,
                            tint = if (done) SuccessGreen else SlateMuted
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = task.titleHi,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "${task.subtitleHi} · +${task.xpReward} XP",
                                style = MaterialTheme.typography.bodySmall,
                                color = SlateMuted
                            )
                        }
                    }
                    TextButton(onClick = { onNavigateTab(task.targetTab) }) {
                        Text("खोलें")
                    }
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = EmeraldLight),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Rounded.School, contentDescription = null, tint = EmeraldPrimary)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = "होम स्क्रीन विजेट टिप",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Text(
                        text = "फोन की होम स्क्रीन को दबाए रखें ➔ Widgets ➔ 'English सीखो Pro' जोड़ें और रोज़ सुबह 1 नया शब्द पाएं।",
                        style = MaterialTheme.typography.bodySmall,
                        color = SlateMuted
                    )
                }
            }
        }
    }
}
