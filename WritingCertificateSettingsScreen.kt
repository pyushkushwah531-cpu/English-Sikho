package com.englishseekho.pro.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsAlertDialog(
    progress: UserProgress,
    onDismiss: () -> Unit,
    onSaveAll: (
        name: String,
        soundEnabled: Boolean,
        vibrationEnabled: Boolean,
        notificationsEnabled: Boolean,
        reminderHour: Int,
        geminiApiKey: String
    ) -> Unit,
    onSendTestNotification: () -> Unit,
    onRetakeLevelTest: () -> Unit
) {
    var nameInput by remember { mutableStateOf(progress.studentName) }
    var sound by remember { mutableStateOf(progress.soundEnabled) }
    var vibration by remember { mutableStateOf(progress.vibrationEnabled) }
    var notify by remember { mutableStateOf(progress.notificationsEnabled) }
    var hour by remember { mutableIntStateOf(progress.reminderHour) }
    var apiKey by remember { mutableStateOf(progress.geminiApiKey) }

    val hourOptions = listOf(
        8 to "8 AM",
        14 to "2 PM",
        19 to "7 PM",
        21 to "9 PM"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "ऐप सेटिंग्स (Pro कंट्रोल)",
                fontWeight = FontWeight.Bold,
                color = EmeraldPrimary
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("छात्र का नाम (सर्टिफिकेट के लिए)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(color = HairlineBorder)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("सही / गलत आवाज़", fontWeight = FontWeight.SemiBold)
                        Text("उत्तर देने पर तुरंत टोन बजेगी", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                    Switch(checked = sound, onCheckedChange = { sound = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("वाइब्रेशन फीडबैक", fontWeight = FontWeight.SemiBold)
                        Text("गलत और जीत पर हल्का वाइब्रेशन", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                    Switch(checked = vibration, onCheckedChange = { vibration = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("दैनिक व टास्क नोटिफिकेशन", fontWeight = FontWeight.SemiBold)
                        Text("रोज़ अभ्यास याद दिलाने के लिए", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                    }
                    Switch(checked = notify, onCheckedChange = { notify = it })
                }

                if (notify) {
                    Text("दैनिक रिमाइंडर समय चुनें:", style = MaterialTheme.typography.labelMedium, color = SlateMuted)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        hourOptions.forEach { (h, label) ->
                            FilterChip(
                                selected = hour == h,
                                onClick = { hour = h },
                                label = { Text(label) }
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = onSendTestNotification,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("टेस्ट नोटिफिकेशन भेजें")
                    }
                }

                HorizontalDivider(color = HairlineBorder)

                Text(
                    text = "वैकल्पिक Free Gemini AI Key (बिल्कुल ज़रूरी नहीं — बाकी सब ऑफलाइन फ्री है):",
                    style = MaterialTheme.typography.bodySmall,
                    color = SlateMuted
                )
                OutlinedTextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    label = { Text("Gemini API Key (AIza...)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedButton(
                    onClick = {
                        onDismiss()
                        onRetakeLevelTest()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("लेवल जांच (Level Test) दोबारा दें")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSaveAll(
                        nameInput.trim().ifBlank { "विद्यार्थी" },
                        sound,
                        vibration,
                        notify,
                        hour,
                        apiKey.trim()
                    )
                    onDismiss()
                }
            ) {
                Text("सेव करें")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("बंद करें")
            }
        }
    )
}

@Composable
fun CertificateAndRankDialog(
    progress: UserProgress,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val unlocked = progress.xp >= 300

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "आपकी रैंक, बैज व सर्टिफिकेट",
                fontWeight = FontWeight.Bold,
                color = EmeraldPrimary
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = EmeraldLight),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("वर्तमान रैंक: ${progress.rankTitleHi()}", fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                        Text("कुल स्कोर: ${progress.xp} XP · ${progress.coins} Gold Coins", style = MaterialTheme.typography.bodySmall)
                        Text("30-दिन कोर्स: ${progress.completedDayNumbers.size}/30 दिन पूर्ण", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Text("अनलॉक किए गए बैज (Badges):", fontWeight = FontWeight.Bold)
                if (progress.unlockedBadges.isEmpty()) {
                    Text(
                        text = "गेम ज़ोन में 'वाक्य पहेली', '60s मिलान' या 'Boss गेम' जीतकर अपने पहले बैज पाएं!",
                        style = MaterialTheme.typography.bodySmall,
                        color = SlateMuted
                    )
                } else {
                    progress.unlockedBadges.forEach { badge ->
                        Text("🏆 $badge", color = SuccessGreen, fontWeight = FontWeight.SemiBold)
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = BorderStroke(1.5.dp, EmeraldPrimary),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("CERTIFICATE OF COMPLETION", style = MaterialTheme.typography.labelMedium, color = EmeraldPrimary, fontWeight = FontWeight.Bold)
                        Text(progress.studentName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                        Text("ने ${progress.level.titleHi} स्तर पर ${progress.xp} XP के साथ अपना अभ्यास जारी रखा।", style = MaterialTheme.typography.bodySmall)
                        Text(
                            text = if (unlocked) "स्थिति: अनलॉक्ड ✅" else "सर्टिफिकेट पक्का करने के लिए ${300 - progress.xp} XP और कमाएं।",
                            color = if (unlocked) SuccessGreen else SlateMuted,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "मैं 'English सीखो Pro' ऐप से English सीख रहा हूँ! मेरी रैंक: ${progress.rankTitleHi()} (${progress.xp} XP, ${progress.coins} Coins)।"
                        )
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "प्रगति शेयर करें"))
                }
            ) {
                Text("शेयर करें")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("बंद करें")
            }
        }
    )
}
