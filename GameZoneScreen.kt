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
import androidx.compose.material3.TextButton
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
    selectedMode: Int = 0,
    onSelectMode: (Int) -> Unit = {},
    onUpdatePuzzleProgress: (levelIndex: Int, solvedCount: Int) -> Unit,
    onUpdateWordMatchBatch: (batchIndex: Int) -> Unit,
    onUpdateBossProgress: (questionIndex: Int, scoreCount: Int) -> Unit,
    onUpdateListeningProgress: (levelIndex: Int, scoreCount: Int) -> Unit = { _, _ -> },
    onRewardEarned: (
        xp: Int,
        coins: Int,
        badge: String?,
        notifyTitle: String?,
        notifyMsg: String?
    ) -> Unit
) {
    val gameMode = selectedMode.coerceIn(0, 3) // 0 = Sentence Builder, 1 = 60s Match, 2 = Boss, 3 = Listening

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
                Text(
                    text = "आपका गेम लेवल अपने-आप सेव रहता है — जहाँ छोड़ें वहीं से आगे खेलें!",
                    style = MaterialTheme.typography.labelSmall,
                    color = EmeraldLight
                )
            }
        }

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = gameMode == 0,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(0)
                },
                label = { Text("वाक्य पहेली (50 लेवल)") }
            )
            FilterChip(
                selected = gameMode == 1,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(1)
                },
                label = { Text("60s मिलान (8 स्टेज)") }
            )
            FilterChip(
                selected = gameMode == 2,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(2)
                },
                label = { Text("Boss गेम (40 लेवल)") }
            )
            FilterChip(
                selected = gameMode == 3,
                onClick = {
                    feedback.playTap(progress.vibrationEnabled)
                    onSelectMode(3)
                },
                label = { Text("सुनो और चुनो (28)") }
            )
        }

        when (gameMode) {
            0 -> SentenceBuilderGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onUpdatePuzzleProgress = onUpdatePuzzleProgress,
                onRewardEarned = onRewardEarned
            )

            1 -> WordMatchSpeedGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onUpdateWordMatchBatch = onUpdateWordMatchBatch,
                onRewardEarned = onRewardEarned
            )

            2 -> WeeklyBossChallengeGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onUpdateBossProgress = onUpdateBossProgress,
                onRewardEarned = onRewardEarned
            )

            else -> ListeningChallengeGame(
                progress = progress,
                speechCoach = speechCoach,
                feedback = feedback,
                onUpdateListeningProgress = onUpdateListeningProgress,
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
    onUpdatePuzzleProgress: (Int, Int) -> Unit,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val puzzles = CurriculumRepository.sentencePuzzles
    var puzzleIndex by remember {
        mutableIntStateOf(progress.puzzleLevelIndex.coerceIn(0, puzzles.lastIndex))
    }
    val puzzle = puzzles[puzzleIndex]

    val poolWords = remember(puzzleIndex) { puzzle.correctWords.shuffled() }
    var selectedIndices by remember(puzzleIndex) { mutableStateOf<List<Int>>(emptyList()) }
    var hearts by remember { mutableIntStateOf(3) }
    var combo by remember { mutableIntStateOf(0) }
    val solvedCount = progress.puzzleSolvedCount
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
                    text = "लेवल ${puzzleIndex + 1} / ${puzzles.size}",
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

            LinearProgressIndicator(
                progress = { (puzzleIndex + 1).toFloat() / puzzles.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = EmeraldPrimary
            )

            if (hearts <= 0) {
                Card(colors = CardDefaults.cardColors(containerColor = ErrorSoft), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("सभी 3 ❤️ खत्म हो गए!", fontWeight = FontWeight.Bold, color = ErrorRed)
                        Text("चिंता न करें — आप अभी भी लेवल ${puzzleIndex + 1} पर ही हैं।", style = MaterialTheme.typography.bodySmall)
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
                                            feedback.playTap(progress.vibrationEnabled)
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
                                feedback.playTap(progress.vibrationEnabled)
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
                        feedback.playTap(progress.vibrationEnabled)
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
                            val nextSolved = solvedCount + 1
                            val nextLevel = (puzzleIndex + 1) % puzzles.size
                            val multiplier = combo.coerceAtMost(3)
                            val earnedXp = 15 * multiplier
                            val earnedCoins = 10 * multiplier
                            feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                            speechCoach.speak(puzzle.correctWords.joinToString(" "), 0.9f)

                            // Save next level immediately so user resumes from next unsolved level
                            onUpdatePuzzleProgress(nextLevel, nextSolved)

                            val milestone = nextSolved % 5 == 0
                            onRewardEarned(
                                earnedXp,
                                earnedCoins,
                                when {
                                    nextSolved >= 25 -> "Sentence Grandmaster"
                                    nextSolved >= 5 -> "Sentence Master"
                                    else -> null
                                },
                                if (milestone) "लेवल ${puzzleIndex + 1} पार! (+$earnedCoins Coins)" else null,
                                if (milestone) "शाबाश! आपने अब तक $nextSolved वाक्य पहेलियाँ जीत ली हैं।" else null
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
                    Text(if (puzzleIndex < puzzles.lastIndex) "अगली पहेली खेलें" else "लेवल 1 से फिर खेलें")
                }
            } else if (statusState == -1) {
                Text(
                    text = "थोड़ा क्रम बदलें! हिंट: ${puzzle.hintHi}",
                    color = ErrorRed,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }

            if (puzzleIndex > 0) {
                TextButton(
                    onClick = {
                        puzzleIndex = 0
                        onUpdatePuzzleProgress(0, solvedCount)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("लेवल 1 से शुरू करें")
                }
            }
        }
    }
}

@Composable
private fun WordMatchSpeedGame(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onUpdateWordMatchBatch: (Int) -> Unit,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val totalBatches = (CurriculumRepository.flashcards.size / 5).coerceAtLeast(1)
    var batchIndex by remember {
        mutableIntStateOf(progress.wordMatchBatchIndex.coerceIn(0, totalBatches - 1))
    }
    val batchStart = batchIndex * 5

    val cards = remember(batchIndex) {
        val total = CurriculumRepository.flashcards.size
        List(5) { idx -> CurriculumRepository.flashcards[(batchStart + idx) % total] }
    }
    val shuffledHindi = remember(batchIndex) { cards.shuffled() }

    var selectedEnglishId by remember(batchIndex) { mutableStateOf<String?>(null) }
    var matchedIds by remember(batchIndex) { mutableStateOf<Set<String>>(emptySet()) }
    var timeLeft by remember { mutableIntStateOf(60) }
    var timerActive by remember { mutableStateOf(false) }
    var totalMatched by remember { mutableIntStateOf(0) }
    var statusNote by remember { mutableStateOf("स्टेज ${batchIndex + 1}/$totalBatches · बाएँ से English शब्द और दाएँ से हिंदी अर्थ चुनें!") }

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
                        text = "${timeLeft}s · स्टेज ${batchIndex + 1}/$totalBatches",
                        fontWeight = FontWeight.Bold,
                        color = if (timeLeft <= 10) ErrorRed else EmeraldPrimary
                    )
                }
                Text(
                    text = "मिलान: $totalMatched",
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
                        matchedIds = emptySet()
                        selectedEnglishId = null
                        timeLeft = 60
                        totalMatched = 0
                        timerActive = true
                        statusNote = "स्टेज ${batchIndex + 1} फिर से शुरू! फटाफट जोड़े मिलाएं।"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("इसी स्टेज से 60s राउंड शुरू करें")
                }
                return@Column
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
                                feedback.playTap(progress.vibrationEnabled)
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
                                        val nextBatch = (batchIndex + 1) % totalBatches
                                        feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
                                        onUpdateWordMatchBatch(nextBatch)
                                        batchIndex = nextBatch
                                        onRewardEarned(
                                            15,
                                            10,
                                            if (totalMatched >= 5) "Speed Matcher" else null,
                                            "स्टेज ${(batchIndex + totalBatches - 1) % totalBatches + 1} साफ़! 🎉",
                                            "शाबाश! अब आप स्टेज ${nextBatch + 1} पर पहुँच गए हैं।"
                                        )
                                        statusNote = "शानदार! नया स्टेज ${nextBatch + 1}/$totalBatches लोड हो गया है।"
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

            if (batchIndex > 0) {
                TextButton(
                    onClick = {
                        batchIndex = 0
                        onUpdateWordMatchBatch(0)
                        statusNote = "स्टेज 1/$totalBatches · बाएँ से English शब्द और दाएँ से हिंदी अर्थ चुनें!"
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("स्टेज 1 से शुरू करें")
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
    onUpdateBossProgress: (Int, Int) -> Unit,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val questions = CurriculumRepository.bossQuestions
    var qIndex by remember {
        mutableIntStateOf(progress.bossQuestionIndex.coerceIn(0, questions.lastIndex))
    }
    var hearts by remember { mutableIntStateOf(3) }
    var selectedOpt by remember(qIndex) { mutableIntStateOf(-1) }
    val score = progress.bossScoreCount

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
                    text = "Boss लेवल ${qIndex + 1} / ${questions.size} · सही: $score",
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

            if (hearts <= 0) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = ErrorSoft),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.EmojiEvents, contentDescription = null, tint = ErrorRed)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "सभी 3 ❤️ खत्म हो गए!",
                                fontWeight = FontWeight.Bold,
                                color = ErrorRed
                            )
                        }
                        Text("आपका लेवल सेव है — आप लेवल ${qIndex + 1} से ही आगे खेल सकते हैं।")
                        Button(
                            onClick = {
                                hearts = 3
                                selectedOpt = -1
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("लेवल ${qIndex + 1} से दोबारा खेलें")
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
                                val nextScore = score + 1
                                val nextIdx = (qIndex + 1) % questions.size
                                feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                onUpdateBossProgress(nextIdx, nextScore)
                                val unlocked = when {
                                    nextScore >= 20 -> "Boss Legend"
                                    nextScore >= 5 -> "Boss Slayer"
                                    else -> null
                                }
                                val milestone = nextScore % 5 == 0
                                onRewardEarned(
                                    20,
                                    10,
                                    unlocked,
                                    if (milestone) "Boss लेवल ${qIndex + 1} जीता! 🏆" else null,
                                    if (milestone) "बधाई हो! आपने अब तक $nextScore Boss सवाल जीते और खास बैज पाया!" else null
                                )
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
                            val nextIdx = (qIndex + 1) % questions.size
                            if (!ok) {
                                onUpdateBossProgress(nextIdx, score)
                            }
                            qIndex = nextIdx
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (qIndex < questions.lastIndex) "अगला सवाल" else "Boss लेवल 1 पर जाएं")
                    }
                }
            }

            if (qIndex > 0) {
                TextButton(
                    onClick = {
                        qIndex = 0
                        selectedOpt = -1
                        onUpdateBossProgress(0, score)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Boss लेवल 1 पर जाएं")
                }
            }
        }
    }
}

@Composable
private fun ListeningChallengeGame(
    progress: UserProgress,
    speechCoach: SpeechCoach,
    feedback: FeedbackManager,
    onUpdateListeningProgress: (Int, Int) -> Unit,
    onRewardEarned: (Int, Int, String?, String?, String?) -> Unit
) {
    val sentences = CurriculumRepository.dailyUseSentences
    var lIndex by remember {
        mutableIntStateOf(progress.listeningLevelIndex.coerceIn(0, sentences.lastIndex))
    }
    val target = sentences[lIndex]
    val score = progress.listeningScoreCount

    val options = remember(lIndex) {
        val distractors = sentences
            .filterIndexed { idx, _ -> idx != lIndex }
            .shuffled()
            .take(3)
            .map { it.hindi }
        (distractors + target.hindi).shuffled()
    }

    var pickedHindi by remember(lIndex) { mutableStateOf("") }
    var revealEnglish by remember(lIndex) { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = BorderStroke(1.5.dp, EmeraldPrimary),
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
                    text = "सुनो और चुनो · लेवल ${lIndex + 1} / ${sentences.size}",
                    fontWeight = FontWeight.Bold,
                    color = EmeraldPrimary
                )
                Text(
                    text = "सही: $score",
                    fontWeight = FontWeight.Bold,
                    color = AmberAccent
                )
            }

            LinearProgressIndicator(
                progress = { (lIndex + 1).toFloat() / sentences.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = EmeraldPrimary
            )

            Text(
                text = "पहले अंग्रेज़ी वाक्य ध्यान से सुनें, फिर नीचे से सही हिंदी अर्थ चुनें:",
                style = MaterialTheme.typography.bodySmall,
                color = SlateMuted
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        feedback.playTap(progress.vibrationEnabled)
                        speechCoach.speak(target.english, 0.8f)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("0.8x धीरे सुनें")
                }
                OutlinedButton(
                    onClick = {
                        feedback.playTap(progress.vibrationEnabled)
                        speechCoach.speak(target.english, 1.0f)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Rounded.VolumeUp, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("1.0x सुनें")
                }
            }

            options.forEach { opt ->
                val isSelected = pickedHindi == opt
                val isCorrect = opt == target.hindi
                val borderCol = when {
                    pickedHindi.isNotBlank() && isCorrect -> SuccessGreen
                    pickedHindi.isNotBlank() && isSelected && !isCorrect -> ErrorRed
                    else -> HairlineBorder
                }
                OutlinedButton(
                    onClick = {
                        if (pickedHindi.isBlank()) {
                            pickedHindi = opt
                            revealEnglish = true
                            if (isCorrect) {
                                val nextScore = score + 1
                                val nextIdx = (lIndex + 1) % sentences.size
                                feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                onUpdateListeningProgress(nextIdx, nextScore)
                                val badge = when {
                                    nextScore >= 15 -> "Listening Legend"
                                    nextScore >= 5 -> "Listening Master"
                                    else -> null
                                }
                                val milestone = nextScore % 5 == 0
                                onRewardEarned(
                                    15,
                                    8,
                                    badge,
                                    if (milestone) "सुनकर पहचानो चैलेंज जीता! 🎧" else null,
                                    if (milestone) "शाबाश! आपने अब तक $nextScore वाक्य सुनकर सही पहचाने।" else null
                                )
                            } else {
                                feedback.playWrong(progress.soundEnabled, progress.vibrationEnabled)
                            }
                        }
                    },
                    border = BorderStroke(1.5.dp, borderCol),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(opt, modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp))
                }
            }

            if (revealEnglish) {
                val ok = pickedHindi == target.hindi
                Card(
                    colors = CardDefaults.cardColors(containerColor = if (ok) SuccessSoft else ErrorSoft),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = if (ok) "बिल्कुल सही! (+15 XP, +8 Coins)" else "सही अर्थ: ${target.hindi}",
                            color = if (ok) SuccessGreen else ErrorRed,
                            fontWeight = FontWeight.Bold
                        )
                        Text("अंग्रेज़ी वाक्य: ${target.english}", fontWeight = FontWeight.SemiBold)
                        Text("उच्चारण: ${target.pronunciationHi}", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                }
                Button(
                    onClick = {
                        val nextIdx = (lIndex + 1) % sentences.size
                        if (!ok) {
                            onUpdateListeningProgress(nextIdx, score)
                        }
                        lIndex = nextIdx
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (lIndex < sentences.lastIndex) "अगला सुनें" else "लेवल 1 पर जाएं")
                }
            }
        }
    }
}

