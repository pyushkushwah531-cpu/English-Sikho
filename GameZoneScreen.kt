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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.services.FeedbackManager
import com.englishseekho.pro.services.SpeechCoach
import com.englishseekho.pro.ui.theme.AmberAccent
import com.englishseekho.pro.ui.theme.AmberLight
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.ErrorRed
import com.englishseekho.pro.ui.theme.ErrorSoft
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen
import com.englishseekho.pro.ui.theme.SuccessSoft
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GameZoneScreen(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onRewardEarned: (
        xp: Int,
        coins: Int,
        badge: String?,
        notifyTitle: String?,
        notifyMsg: String?
    ) -> Unit
) {
    var gameMode by remember { mutableIntStateOf(0) } // 0 = Sentence Builder, 1 = 60s Word Match, 2 = Boss Challenge

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Gaming HUD Header
        Card(
            colors = CardDefaults.cardColors(containerColor = EmeraldPrimary),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "गेम ज़ोन · खेल-खेल में English सीखें",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "रैंक: ${progress.rankTitleHi()} · ${progress.xp} XP · ${progress.coins} Coins",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmeraldLight,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = gameMode == 0,
                onClick = { gameMode = 0 },
                label = { Text("वाक्य पहेली (Sentence)") }
            )
            FilterChip(
                selected = gameMode == 1,
                onClick = { gameMode = 1 },
                label = { Text("60s शब्द मिलान") }
            )
            FilterChip(
                selected = gameMode == 2,
                onClick = { gameMode = 2 },
                label = { Text("साप्ताहिक Boss गेम") }
            )
        }

        when (gameMode) {
            0 -> SentenceBuilderGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onRewardEarned = onRewardEarned
            )

            1 -> WordMatchSpeedGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onRewardEarned = onRewardEarned
            )

            else -> WeeklyBossChallengeGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onRewardEarned = onRewardEarned
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun SentenceBuilderGame(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val puzzles = CurriculumRepository.sentencePuzzles
    var puzzleIndex by remember { mutableIntStateOf(0) }
    val puzzle = puzzles[puzzleIndex]

    val poolWords = remember(puzzleIndex) { puzzle.correctWords.shuffled() }
    var selectedIndices by remember(puzzleIndex) { mutableStateOf<List<Int>>(emptyList()) }
    var hearts by remember { mutableIntStateOf(3) }
    var combo by remember { mutableIntStateOf(0) }
    var solvedCount by remember { mutableIntStateOf(0) }
    var statusState by remember(puzzleIndex) { mutableIntStateOf(0) } // 0 = playing, 1 = correct, -1 = wrong

    val builtWords = selectedIndices.map { poolWords[it] }

    Card(
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = BorderStroke(1.dp, HairlineBorder),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "पहेली ${puzzleIndex + 1}/${puzzles.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = EmeraldPrimary,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (combo >= 2) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.Bolt, contentDescription = null, tint = AmberAccent)
                            Text(
                                text = "${combo.coerceAtMost(3)}x Combo",
                                color = AmberAccent,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(3) { i ->
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = null,
                                tint = if (i < hearts) ErrorRed else HairlineBorder
                            )
                        }
                    }
                }
            }

            if (hearts <= 0) {
                Card(colors = CardDefaults.cardColors(containerColor = ErrorSoft), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("सभी 3 ❤️ खत्म हो गए!", fontWeight = FontWeight.Bold, color = ErrorRed)
                        Text("चिंता न करें — दोबारा प्रयास करें और अपनी Streak बनाएं।", style = MaterialTheme.typography.bodySmall)
                        Button(
                            onClick = {
                                hearts = 3
                                combo = 0
                                selectedIndices = emptyList()
                                statusState = 0
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Rounded.Refresh, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("नई ऊर्जा (3 ❤️) के साथ खेलें")
                        }
                    }
                }
                return@Column
            }

            Text(
                text = "इस हिंदी वाक्य की सही English बनाएं:",
                style = MaterialTheme.typography.labelMedium,
                color = SlateMuted
            )
            Text(
                text = puzzle.hindiPrompt,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // Answer assembly box
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = when (statusState) {
                        1 -> SuccessSoft
                        -1 -> ErrorSoft
                        else -> EmeraldLight
                    }
                ),
                border = BorderStroke(
                    1.dp,
                    when (statusState) {
                        1 -> SuccessGreen
                        -1 -> ErrorRed
                        else -> EmeraldPrimary
                    }
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (builtWords.isEmpty()) {
                        Text(
                            text = "नीचे दिए शब्दों पर सही क्रम में टैप करें...",
                            style = MaterialTheme.typography.bodySmall,
                            color = SlateMuted
                        )
                    } else {
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            selectedIndices.forEachIndexed { pos, poolIdx ->
                                FilterChip(
                                    selected = true,
                                    onClick = {
                                        if (statusState != 1) {
                                            selectedIndices = selectedIndices.toMutableList().also { it.removeAt(pos) }
                                            statusState = 0
                                        }
                                    },
                                    label = { Text(poolWords[poolIdx], fontWeight = FontWeight.Bold) }
                                )
                            }
                        }
                    }
                }
            }

            // Word choices pool
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                poolWords.forEachIndexed { idx, word ->
                    val isUsed = selectedIndices.contains(idx)
                    OutlinedButton(
                        onClick = {
                            if (!isUsed && statusState != 1) {
                                selectedIndices = selectedIndices + idx
                                statusState = 0
                            }
                        },
                        enabled = !isUsed && statusState != 1
                    ) {
                        Text(word)
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = {
                        selectedIndices = emptyList()
                        statusState = 0
                    },
                    enabled = statusState != 1 && selectedIndices.isNotEmpty(),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("साफ़ करें")
                }

                Button(
                    onClick = {
                        if (builtWords == puzzle.correctWords) {
                            statusState = 1
                            combo += 1
                            solvedCount += 1
                            val multiplier = combo.coerceAtMost(3)
                            val earnedXp = 15 * multiplier
                            val earnedCoins = 10 * multiplier
                            feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                            speechCoach.speak(puzzle.correctWords.joinToString(" "), 0.9f)

                            val milestone = solvedCount % 3 == 0
                            onRewardEarned(
                                earnedXp,
                                earnedCoins,
                                if (solvedCount >= 3) "Sentence Master" else null,
                                if (milestone) "वाक्य पहेली चैंपियन! (+${earnedCoins} Coins)" else null,
                                if (milestone) "शाबाश! आपने $solvedCount वाक्य पहेलियाँ जीत लीं और $earnedXp XP पाए।" else null
                            )
                        } else {
                            statusState = -1
                            combo = 0
                            hearts = (hearts - 1).coerceAtLeast(0)
                            feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                        }
                    },
                    enabled = builtWords.size == puzzle.correctWords.size && statusState != 1,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("उत्तर जाँचें")
                }
            }

            if (statusState == 1) {
                Text(
                    text = "बिल्कुल सही! ✅ (${puzzle.correctWords.joinToString(" ")}) · Combo ${combo.coerceAtMost(3)}x",
                    color = SuccessGreen,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        puzzleIndex = (puzzleIndex + 1) % puzzles.size
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("अगली पहेली खेलें")
                }
            } else if (statusState == -1) {
                Text(
                    text = "थोड़ा क्रम बदलें! हिंट: ${puzzle.hintHi}",
                    color = ErrorRed,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun WordMatchSpeedGame(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    var batchStart by remember { mutableIntStateOf(0) }
    val cards = remember(batchStart) {
        val total = CurriculumRepository.flashcards.size
        List(5) { idx -> CurriculumRepository.flashcards[(batchStart + idx) % total] }
    }
    val shuffledHindi = remember(batchStart) { cards.shuffled() }

    var selectedEnglishId by remember(batchStart) { mutableStateOf<String?>(null) }
    var matchedIds by remember(batchStart) { mutableStateOf<Set<String>>(emptySet()) }
    var timeLeft by remember { mutableIntStateOf(60) }
    var timerActive by remember { mutableStateOf(false) }
    var totalMatched by remember { mutableIntStateOf(0) }
    var statusNote by remember { mutableStateOf("बाएँ से English शब्द और दाएँ से उसका सही हिंदी अर्थ चुनें!") }

    LaunchedEffect(timerActive, timeLeft) {
        if (timerActive && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else if (timerActive && timeLeft == 0) {
            timerActive = false
            feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
            onRewardEarned(
                totalMatched * 5,
                totalMatched * 3,
                if (totalMatched >= 5) "Speed Matcher" else null,
                "60-सेकंड चैलेंज पूरा!",
                "आपने 60 सेकंड में $totalMatched शब्दों के सही जोड़े मिलाए!"
            )
            statusNote = "समय समाप्त! आपका स्कोर: $totalMatched सही मिलान।"
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = BorderStroke(1.dp, HairlineBorder),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Timer, contentDescription = null, tint = if (timeLeft <= 10) ErrorRed else EmeraldPrimary)
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "${timeLeft}s शेष",
                        fontWeight = FontWeight.Bold,
                        color = if (timeLeft <= 10) ErrorRed else EmeraldPrimary
                    )
                }
                Text(
                    text = "सही मिलान: $totalMatched",
                    fontWeight = FontWeight.Bold,
                    color = AmberAccent
                )
            }

            LinearProgressIndicator(
                progress = { timeLeft / 60f },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = EmeraldPrimary
            )

            Text(statusNote, style = MaterialTheme.typography.bodySmall, color = SlateMuted)

            if (timeLeft == 0) {
                Button(
                    onClick = {
                        timeLeft = 60
                        totalMatched = 0
                        batchStart = (batchStart + 5) % CurriculumRepository.flashcards.size
                        timerActive = true
                        statusNote = "नया राउंड शुरू! फटाफट जोड़े मिलाएं।"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("नया 60s राउंड शुरू करें")
                }
                return@Column
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Left: English words
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    cards.forEach { card ->
                        val isMatched = matchedIds.contains(card.id)
                        val isSelected = selectedEnglishId == card.id
                        Button(
                            onClick = {
                                if (!timerActive) timerActive = true
                                selectedEnglishId = card.id
                                speechCoach.speak(card.wordEn, 0.9f)
                            },
                            enabled = !isMatched,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when {
                                    isMatched -> SuccessSoft
                                    isSelected -> EmeraldPrimary
                                    else -> EmeraldLight
                                },
                                contentColor = when {
                                    isMatched -> SuccessGreen
                                    isSelected -> MaterialTheme.colorScheme.onPrimary
                                    else -> EmeraldPrimary
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isMatched) "✓ ${card.wordEn}" else card.wordEn, maxLines = 1)
                        }
                    }
                }

                // Right: Hindi meanings
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    shuffledHindi.forEach { hCard ->
                        val isMatched = matchedIds.contains(hCard.id)
                        OutlinedButton(
                            onClick = {
                                if (!timerActive) timerActive = true
                                val chosenEn = selectedEnglishId
                                if (chosenEn == null) {
                                    statusNote = "पहले बाईं तरफ से एक English शब्द चुनें।"
                                    return@OutlinedButton
                                }
                                if (chosenEn == hCard.id) {
                                    val newMatched = matchedIds + hCard.id
                                    matchedIds = newMatched
                                    selectedEnglishId = null
                                    totalMatched += 1
                                    feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                    onRewardEarned(8, 5, null, null, null)
                                    statusNote = "सही जोड़ी! +8 XP · +5 Coins"

                                    if (newMatched.size == cards.size) {
                                        feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
                                        onRewardEarned(
                                            15,
                                            10,
                                            if (totalMatched >= 5) "Speed Matcher" else null,
                                            "Word Match सेट साफ़!",
                                            "शाबाश! 5/5 सही मिलान करने पर +15 XP बोनस मिला।"
                                        )
                                        batchStart = (batchStart + 5) % CurriculumRepository.flashcards.size
                                    }
                                } else {
                                    feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                                    selectedEnglishId = null
                                    statusNote = "गलत मिलान ❌ — सही अर्थ चुनने की कोशिश करें।"
                                }
                            },
                            enabled = !isMatched,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = hCard.meaningHi.substringBefore(" /"),
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeeklyBossChallengeGame(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val questions = CurriculumRepository.bossQuestions
    var qIndex by remember { mutableIntStateOf(0) }
    var hearts by remember { mutableIntStateOf(3) }
    var selectedOpt by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = BorderStroke(1.5.dp, AmberAccent),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "साप्ताहिक Boss चैलेंज (${qIndex + 1}/${questions.size})",
                    fontWeight = FontWeight.Bold,
                    color = AmberAccent
                )
                Row {
                    repeat(3) { i ->
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = null,
                            tint = if (i < hearts) ErrorRed else HairlineBorder
                        )
                    }
                }
            }

            if (finished || hearts <= 0) {
                val won = hearts > 0 && score >= 5
                Card(
                    colors = CardDefaults.cardColors(containerColor = if (won) AmberLight else ErrorSoft),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.EmojiEvents, contentDescription = null, tint = if (won) AmberAccent else ErrorRed)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (won) "बॉस चैलेंज विजेता! 🏆" else "कोई बात नहीं — फिर से कोशिश करें!",
                                fontWeight = FontWeight.Bold,
                                color = if (won) AmberAccent else ErrorRed
                            )
                        }
                        Text("आपका स्कोर: $score / ${questions.size} सही जवाब।")
                        Button(
                            onClick = {
                                qIndex = 0
                                hearts = 3
                                selectedOpt = -1
                                score = 0
                                finished = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Boss चैलेंज दोबारा खेलें")
                        }
                    }
                }
                return@Column
            }

            val q = questions[qIndex]
            LinearProgressIndicator(
                progress = { (qIndex + 1).toFloat() / questions.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = AmberAccent
            )
            Text(q.promptHi, style = MaterialTheme.typography.labelLarge, color = SlateMuted)
            Text(q.questionEn, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            q.options.forEachIndexed { idx, opt ->
                val isSelected = selectedOpt == idx
                val isRight = idx == q.correctIndex
                val borderCol = when {
                    selectedOpt != -1 && isRight -> SuccessGreen
                    selectedOpt != -1 && isSelected && !isRight -> ErrorRed
                    else -> HairlineBorder
                }
                OutlinedButton(
                    onClick = {
                        if (selectedOpt == -1) {
                            selectedOpt = idx
                            if (isRight) {
                                score += 1
                                feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                onRewardEarned(20, 10, null, null, null)
                            } else {
                                hearts = (hearts - 1).coerceAtLeast(0)
                                feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                            }
                        }
                    },
                    border = BorderStroke(1.5.dp, borderCol),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(opt, modifier = Modifier.fillMaxWidth())
                }
            }

            if (selectedOpt != -1) {
                val ok = selectedOpt == q.correctIndex
                Text(
                    text = if (ok) "सही! (+20 XP, +10 Coins) ${q.explanationHi}" else "गलत ❌ — ${q.explanationHi}",
                    color = if (ok) SuccessGreen else ErrorRed,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { speechCoach.speak(q.questionEn.replace("___", q.options[q.correctIndex])) }
                    ) {
                        Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                    }
                    Button(
                        onClick = {
                            if (qIndex < questions.lastIndex) {
                                qIndex += 1
                                selectedOpt = -1
                            } else {
                                finished = true
                                if (score >= 5) {
                                    feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
                                    onRewardEarned(
                                        50,
                                        30,
                                        "Boss Slayer",
                                        "Boss Challenge जीता! 🏆",
                                        "बधाई हो! आपने 'Boss Slayer' बैज अनलॉक किया और +50 XP + 30 Coins जीते!"
                                    )
                                }
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (qIndex < questions.lastIndex) "अगला Boss सवाल" else "परिणाम देखें")
                    }
                }
            }
        }
    }
}
