package com.englishseekho.pro

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.englishseekho.pro.data.LocalProgressStore
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.notifications.NotificationHelper
import com.englishseekho.pro.services.FeedbackManager
import com.englishseekho.pro.services.SpeechCoach
import com.englishseekho.pro.ui.screens.CertificateAndRankDialog
import com.englishseekho.pro.ui.screens.GameZoneScreen
import com.englishseekho.pro.ui.screens.HindiBridgeAndGrammarScreen
import com.englishseekho.pro.ui.screens.HomeDashboardScreen
import com.englishseekho.pro.ui.screens.LevelTestScreen
import com.englishseekho.pro.ui.screens.SettingsAlertDialog
import com.englishseekho.pro.ui.screens.SpeakingStudioScreen
import com.englishseekho.pro.ui.screens.VocabAndStoriesScreen
import com.englishseekho.pro.ui.theme.AmberLight
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.EnglishSeekhoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private data class NavStep(val tab: Int, val sub: Int = 0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishSeekhoTheme {
                EnglishSeekhoRootApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishSeekhoRootApp() {
    val context = LocalContext.current
    val store = remember { LocalProgressStore(context) }
    val speechCoach = remember { SpeechCoach(context) }
    val feedback = remember { FeedbackManager(context) }

    var progress by remember { mutableStateOf(store.load()) }
    var showSplash by remember { mutableStateOf(true) }
    var navStack by remember { mutableStateOf(listOf(NavStep(0, 0))) }
    val currentStep = navStack.last()
    val selectedTab = currentStep.tab
    val currentSub = currentStep.sub

    var showLevelTest by remember { mutableStateOf(!progress.onboardingDone) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var showCertificateDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }

    var hasMicPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED
        )
    }

    val micLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasMicPermission = granted
    }

    val notificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ -> }

    LaunchedEffect(Unit) {
        NotificationHelper.ensureChannels(context)
        NotificationHelper.scheduleDailyReminder(
            context = context,
            enabled = progress.notificationsEnabled,
            hourOfDay = progress.reminderHour
        )
    }

    LaunchedEffect(Unit) {
        delay(3000L)
        showSplash = false
    }

    DisposableEffect(Unit) {
        onDispose {
            speechCoach.release()
            feedback.release()
        }
    }

    fun updateProgress(transform: (UserProgress) -> UserProgress) {
        val updated = transform(progress)
        progress = updated
        store.save(updated)
    }

    fun navigateTo(tab: Int, sub: Int = 0) {
        val target = NavStep(tab, sub)
        if (navStack.lastOrNull() != target) {
            navStack = (navStack + target).takeLast(40)
        }
    }

    fun stepBack(): Boolean {
        return when {
            showSplash -> true
            showSettingsDialog -> {
                showSettingsDialog = false
                true
            }
            showCertificateDialog -> {
                showCertificateDialog = false
                true
            }
            showExitDialog -> {
                showExitDialog = false
                true
            }
            showLevelTest && progress.onboardingDone -> {
                showLevelTest = false
                true
            }
            navStack.size > 1 -> {
                feedback.playTap(progress.vibrationEnabled)
                navStack = navStack.dropLast(1)
                true
            }
            else -> false
        }
    }

    // Pro-Level Step-by-Step Back Navigation System
    BackHandler {
        if (!stepBack()) {
            showExitDialog = true
        }
    }

    Crossfade(targetState = showSplash, label = "splash_transition") { isSplash ->
        if (isSplash) {
            SplashIntroScreen()
        } else if (showLevelTest) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            if (progress.onboardingDone) {
                                IconButton(onClick = { stepBack() }) {
                                    Icon(Icons.Rounded.ArrowBack, contentDescription = "पीछे जाएं", tint = EmeraldLight)
                                }
                            }
                        },
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_app_logo_unified),
                                    contentDescription = "English सीखो Pro Logo",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text("English सीखो Pro", fontWeight = FontWeight.Bold)
                            }
                        },
                        actions = {
                            IconButton(onClick = { showSettingsDialog = true }) {
                                Icon(Icons.Rounded.Settings, contentDescription = "सेटिंग्स", tint = EmeraldLight)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = EmeraldPrimary,
                            titleContentColor = EmeraldLight
                        )
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                LevelTestScreen(
                    initialName = progress.studentName,
                    feedback = feedback,
                    soundEnabled = progress.soundEnabled,
                    vibrationEnabled = progress.vibrationEnabled,
                    onComplete = { name, level, bonusXp ->
                        updateProgress {
                            it.copy(
                                studentName = name,
                                level = level,
                                xp = it.xp + bonusXp,
                                coins = it.coins + 20,
                                onboardingDone = true
                            )
                        }
                        if (progress.notificationsEnabled) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                                !NotificationHelper.hasNotificationPermission(context)
                            ) {
                                notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            } else {
                                NotificationHelper.showTaskCompletedNotification(
                                    context,
                                    "लेवल टेस्ट पूरा! (+${bonusXp} XP)",
                                    "आपका 30-दिन का प्लान शुरू हो गया है।"
                                )
                            }
                        }
                        showLevelTest = false
                    }
                )
            }
        }
    } else {
        val navItems = listOf(
            "होम" to Icons.Rounded.Home,
            "खेलें" to Icons.Rounded.SportsEsports,
            "बोलें" to Icons.Rounded.Mic,
            "ग्रामर" to Icons.Rounded.Translate,
            "शब्द" to Icons.Rounded.MenuBook
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        if (navStack.size > 1) {
                            IconButton(onClick = { stepBack() }) {
                                Icon(Icons.Rounded.ArrowBack, contentDescription = "पीछे जाएं", tint = EmeraldLight)
                            }
                        }
                    },
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_app_logo_unified),
                                contentDescription = "English सीखो Pro Logo",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(9.dp))
                            )
                            Column {
                                Text("English सीखो Pro", fontWeight = FontWeight.Bold)
                                Text(
                                    text = "${progress.xp} XP · ${progress.coins} Coins",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = EmeraldLight
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = { showCertificateDialog = true }) {
                            Icon(Icons.Rounded.EmojiEvents, contentDescription = "रैंक व सर्टिफिकेट", tint = EmeraldLight)
                        }
                        IconButton(onClick = { showSettingsDialog = true }) {
                            Icon(Icons.Rounded.Settings, contentDescription = "सेटिंग्स", tint = EmeraldLight)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = EmeraldPrimary,
                        titleContentColor = EmeraldLight
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed { index, (label, icon) ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = {
                                feedback.playTap(progress.vibrationEnabled)
                                navigateTo(index, 0)
                            },
                            icon = { Icon(icon, contentDescription = label) },
                            label = { Text(label, maxLines = 1) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (selectedTab) {
                    0 -> HomeDashboardScreen(
                        progress = progress,
                        speechCoach = speechCoach,
                        onToggleTask = { taskId, reward ->
                            val wasDone = progress.completedTaskIds.contains(taskId)
                            updateProgress { p ->
                                val already = p.completedTaskIds.contains(taskId)
                                val newSet = if (already) p.completedTaskIds - taskId else p.completedTaskIds + taskId
                                val newXp = if (already) (p.xp - reward).coerceAtLeast(0) else p.xp + reward
                                val newCoins = if (already) (p.coins - 10).coerceAtLeast(0) else p.coins + 10
                                p.copy(completedTaskIds = newSet, xp = newXp, coins = newCoins)
                            }
                            if (!wasDone) {
                                feedback.playCorrect(progress.soundEnabled, progress.vibrationEnabled)
                                if (progress.notificationsEnabled) {
                                    NotificationHelper.showTaskCompletedNotification(
                                        context,
                                        "दैनिक लक्ष्य पूरा! (+$reward XP)",
                                        "बहुत बढ़िया ${progress.studentName}! आज का अभ्यास आगे बढ़ रहा है।"
                                    )
                                }
                            }
                        },
                        onToggleCourseDay = { dayNumber ->
                            val wasDone = progress.completedDayNumbers.contains(dayNumber)
                            updateProgress { p ->
                                val already = p.completedDayNumbers.contains(dayNumber)
                                val newDays = if (already) p.completedDayNumbers - dayNumber else p.completedDayNumbers + dayNumber
                                val newXp = if (already) (p.xp - 20).coerceAtLeast(0) else p.xp + 20
                                val newCoins = if (already) (p.coins - 10).coerceAtLeast(0) else p.coins + 10
                                val newBadges = if (!already && newDays.size >= 30) p.unlockedBadges + "30-Day Finisher" else p.unlockedBadges
                                p.copy(completedDayNumbers = newDays, xp = newXp, coins = newCoins, unlockedBadges = newBadges)
                            }
                            if (!wasDone) {
                                feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
                                if (progress.notificationsEnabled) {
                                    NotificationHelper.showTaskCompletedNotification(
                                        context,
                                        "दिन $dayNumber पूरा हुआ! 🎉",
                                        "शाबाश! आपने 30-दिन कोर्स का दिन $dayNumber सफलतापूर्वक पूरा किया।"
                                    )
                                }
                            }
                        },
                        onNavigateTab = {
                            feedback.playTap(progress.vibrationEnabled)
                            navigateTo(it, 0)
                        },
                        onOpenWritingCoach = {
                            feedback.playTap(progress.vibrationEnabled)
                            navigateTo(3, 4)
                        },
                        onOpenCertificateDialog = { showCertificateDialog = true },
                        onRetakeLevelTest = { showLevelTest = true }
                    )

                    1 -> GameZoneScreen(
                        progress = progress,
                        speechCoach = speechCoach,
                        feedback = feedback,
                        selectedMode = if (selectedTab == 1) currentSub else 0,
                        onSelectMode = { navigateTo(1, it) },
                        onUpdatePuzzleProgress = { levelIdx, solved ->
                            updateProgress {
                                it.copy(
                                    puzzleLevelIndex = levelIdx,
                                    puzzleSolvedCount = solved
                                )
                            }
                        },
                        onUpdateWordMatchBatch = { batchIdx ->
                            updateProgress {
                                it.copy(wordMatchBatchIndex = batchIdx)
                            }
                        },
                        onUpdateBossProgress = { qIdx, bossScore ->
                            updateProgress {
                                it.copy(
                                    bossQuestionIndex = qIdx,
                                    bossScoreCount = bossScore
                                )
                            }
                        },
                        onUpdateListeningProgress = { lIdx, lScore ->
                            updateProgress {
                                it.copy(
                                    listeningLevelIndex = lIdx,
                                    listeningScoreCount = lScore
                                )
                            }
                        },
                        onRewardEarned = { xp, coins, badge, notifyTitle, notifyMsg ->
                            updateProgress { p ->
                                p.copy(
                                    xp = p.xp + xp,
                                    coins = p.coins + coins,
                                    unlockedBadges = if (badge != null) p.unlockedBadges + badge else p.unlockedBadges
                                )
                            }
                            if (progress.notificationsEnabled && notifyTitle != null && notifyMsg != null) {
                                NotificationHelper.showTaskCompletedNotification(context, notifyTitle, notifyMsg)
                            }
                        }
                    )

                    2 -> SpeakingStudioScreen(
                        progress = progress,
                        speechCoach = speechCoach,
                        feedback = feedback,
                        hasMicPermission = hasMicPermission,
                        onRequestMicPermission = { micLauncher.launch(Manifest.permission.RECORD_AUDIO) },
                        selectedMode = if (selectedTab == 2) currentSub else 0,
                        onSelectMode = { navigateTo(2, it) },
                        onAddXp = { bonus -> updateProgress { it.copy(xp = it.xp + bonus, coins = it.coins + 5) } }
                    )

                    3 -> HindiBridgeAndGrammarScreen(
                        progress = progress,
                        speechCoach = speechCoach,
                        feedback = feedback,
                        initialSection = if (selectedTab == 3) currentSub else 0,
                        onSelectSection = { navigateTo(3, it) },
                        onAddXp = { bonus -> updateProgress { it.copy(xp = it.xp + bonus, coins = it.coins + 5) } }
                    )

                    else -> VocabAndStoriesScreen(
                        progress = progress,
                        speechCoach = speechCoach,
                        feedback = feedback,
                        selectedTab = if (selectedTab == 4) currentSub else 0,
                        onSelectTab = { navigateTo(4, it) },
                        onMarkWord = { wordId, mastered ->
                            updateProgress { p ->
                                if (mastered) {
                                    p.copy(
                                        masteredWordIds = p.masteredWordIds + wordId,
                                        reviewWordIds = p.reviewWordIds - wordId
                                    )
                                } else {
                                    p.copy(
                                        reviewWordIds = p.reviewWordIds + wordId,
                                        masteredWordIds = p.masteredWordIds - wordId
                                    )
                                }
                            }
                        },
                        onAddXp = { bonus -> updateProgress { it.copy(xp = it.xp + bonus, coins = it.coins + 5) } }
                    )
                }
            }
        }
    }
    }

    if (showSettingsDialog) {
        SettingsAlertDialog(
            progress = progress,
            onDismiss = { showSettingsDialog = false },
            onSaveAll = { name, soundOn, vibOn, notifOn, remHour, key ->
                if (notifOn && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    !NotificationHelper.hasNotificationPermission(context)
                ) {
                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                updateProgress {
                    it.copy(
                        studentName = name,
                        soundEnabled = soundOn,
                        vibrationEnabled = vibOn,
                        notificationsEnabled = notifOn,
                        reminderHour = remHour,
                        geminiApiKey = key
                    )
                }
                NotificationHelper.scheduleDailyReminder(context, notifOn, remHour)
                feedback.playCorrect(soundOn, vibOn)
            },
            onSendTestNotification = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    !NotificationHelper.hasNotificationPermission(context)
                ) {
                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    NotificationHelper.showDailyReminderNotification(context)
                    feedback.playCelebration(progress.soundEnabled, progress.vibrationEnabled)
                }
            },
            onRetakeLevelTest = { showLevelTest = true }
        )
    }

    if (showCertificateDialog) {
        CertificateAndRankDialog(
            progress = progress,
            onDismiss = { showCertificateDialog = false }
        )
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("ऐप बंद करें?", fontWeight = FontWeight.Bold) },
            text = { Text("आपका सारा अभ्यास (${progress.xp} XP और ${progress.coins} Coins) सुरक्षित सेव हो चुका है। क्या आप बाहर निकलना चाहते हैं?") },
            confirmButton = {
                Button(onClick = { (context as? Activity)?.finish() }) {
                    Text("हाँ, बाहर निकलें")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("अभ्यास जारी रखें")
                }
            }
        )
    }
}

@Composable
private fun SplashIntroScreen() {
    val iconScale = remember { Animatable(0.65f) }
    val contentAlpha = remember { Animatable(0f) }
    val barProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            contentAlpha.animateTo(1f, animationSpec = tween(650, easing = FastOutSlowInEasing))
        }
        launch {
            iconScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
        launch {
            barProgress.animateTo(1f, animationSpec = tween(2850, easing = LinearEasing))
        }
    }

    val warmGold = Color(0xFFFBBF24)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF07332A),
                        EmeraldPrimary,
                        Color(0xFF14725F)
                    )
                )
            )
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(contentAlpha.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(126.dp)
                    .scale(iconScale.value)
                    .clip(RoundedCornerShape(32.dp))
                    .border(2.5.dp, warmGold, RoundedCornerShape(32.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo_unified),
                    contentDescription = "English सीखो Pro Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "English सीखो Pro",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .width((150 * barProgress.value.coerceAtMost(0.45f) / 0.45f).dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(warmGold)
            )

            Text(
                text = "आत्मविश्वास से अंग्रेज़ी बोलें · हिंदी से English",
                style = MaterialTheme.typography.bodyMedium,
                color = AmberLight,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LinearProgressIndicator(
                progress = { barProgress.value },
                modifier = Modifier
                    .width(210.dp)
                    .height(6.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = warmGold,
                trackColor = Color(0x33FFFFFF)
            )
            Text(
                text = "v1.3.2 · 100% ऑफलाइन तैयार",
                style = MaterialTheme.typography.labelMedium,
                color = EmeraldLight
            )
        }
    }
}
