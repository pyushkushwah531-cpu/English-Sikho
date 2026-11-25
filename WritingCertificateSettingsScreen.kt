package com.englishseekho.pro.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color as AndroidColor
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.englishseekho.pro.R
import com.englishseekho.pro.data.UserProgress
import com.englishseekho.pro.ui.theme.CardSurface
import com.englishseekho.pro.ui.theme.EmeraldLight
import com.englishseekho.pro.ui.theme.EmeraldPrimary
import com.englishseekho.pro.ui.theme.HairlineBorder
import com.englishseekho.pro.ui.theme.SlateMuted
import com.englishseekho.pro.ui.theme.SuccessGreen
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    var showAdvanced by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

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

                OutlinedButton(
                    onClick = {
                        runCatching {
                            val shareText = "मैं 'English सीखो Pro' ऐप से रोज़ बिल्कुल फ्री और 100% ऑफलाइन अंग्रेज़ी बोलना सीख रहा हूँ!\n\nइसमें 30-दिन का कोर्स, 1,430+ इंग्लिश गेम लेवल, माइक से उच्चारण जाँच और जॉब इंटरव्यू तैयारी सब कुछ हिंदी में है।\n\nजानकारी व Privacy Policy: https://pyushkushwah531-cpu.github.io/English-Sikho/privacy.html"
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "English सीखो Pro ऐप")
                                putExtra(Intent.EXTRA_TEXT, shareText)
                            }
                            context.startActivity(Intent.createChooser(intent, "दोस्तों को ऐप शेयर करें"))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("दोस्तों को ऐप शेयर करें 📤")
                }

                OutlinedButton(
                    onClick = { showPrivacyDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Privacy Policy व ऐप जानकारी (v1.3.2)")
                }

                OutlinedButton(
                    onClick = {
                        onDismiss()
                        onRetakeLevelTest()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("लेवल जांच (Level Test) दोबारा दें")
                }

                TextButton(
                    onClick = { showAdvanced = !showAdvanced },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (showAdvanced) "Advanced Options छिपाएं ▲" else "Advanced Options (वैकल्पिक AI Key) ▼")
                }

                if (showAdvanced) {
                    Text(
                        text = "यह बिल्कुल वैकल्पिक है — इसके बिना भी पूरा ऐप 100% ऑफलाइन और फ्री चलता है:",
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

    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = {
                Text(
                    text = "Privacy Policy व ऐप जानकारी",
                    fontWeight = FontWeight.Bold,
                    color = EmeraldPrimary
                )
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier.matchParentSize()
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "English सीखो Pro Logo",
                                modifier = Modifier.matchParentSize()
                            )
                        }
                        Column {
                            Text("ऐप: English सीखो Pro (v1.3.2)", fontWeight = FontWeight.Bold)
                            Text("Package ID: com.englishseekho.pro", style = MaterialTheme.typography.bodySmall, color = SlateMuted)
                        }
                    }
                    HorizontalDivider(color = HairlineBorder)
                    Text("1. आपका सारा डेटा आपके फोन में सुरक्षित:", fontWeight = FontWeight.SemiBold)
                    Text(
                        "आपका नाम, XP, Coins, गेम लेवल और 30-दिन प्रगति केवल आपके फोन की लोकल मेमोरी में सेव रहती है। हम कोई व्यक्तिगत डेटा अपने सर्वर पर नहीं भेजते।",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text("2. माइक (Microphone) अनुमति:", fontWeight = FontWeight.SemiBold)
                    Text(
                        "माइक का उपयोग केवल तभी होता है जब आप 'बोलें' या 'उच्चारण जाँचें' बटन दबाते हैं। आपकी आवाज़ कहीं रिकॉर्ड या स्टोर नहीं की जाती।",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text("3. नोटिफिकेशन अनुमति:", fontWeight = FontWeight.SemiBold)
                    Text(
                        "केवल आपके द्वारा चुने गए समय पर रोज़ाना पढ़ाई की याद दिलाने के लिए उपयोग किया जाता है। इसे सेटिंग्स से कभी भी बंद कर सकते हैं।",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text("4. संपर्क व सहायता:", fontWeight = FontWeight.SemiBold)
                    Text("ईमेल: pyushkushwah531@gmail.com", style = MaterialTheme.typography.bodySmall, color = EmeraldPrimary)
                    Text("5. ऑनलाइन Privacy Policy लिंक:", fontWeight = FontWeight.SemiBold)
                    Text(
                        "https://pyushkushwah531-cpu.github.io/English-Sikho/privacy.html",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmeraldPrimary
                    )
                    OutlinedButton(
                        onClick = {
                            runCatching {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://pyushkushwah531-cpu.github.io/English-Sikho/privacy.html")
                                )
                                context.startActivity(intent)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ऑनलाइन Privacy Policy खोलें 🌐")
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showPrivacyDialog = false }) {
                    Text("समझ गया")
                }
            }
        )
    }
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
                onClick = { shareCertificateImage(context, progress) }
            ) {
                Text("फोटो शेयर करें (WhatsApp)")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("बंद करें")
            }
        }
    )
}

private fun shareCertificateImage(context: Context, progress: UserProgress) {
    val width = 1200
    val height = 800
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Background
    val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#F7F9F8")
    }
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

    // Emerald top banner
    val headerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#0F5C4D")
    }
    canvas.drawRect(0f, 0f, width.toFloat(), 170f, headerPaint)

    // Outer Emerald border & Inner Gold border
    val borderEmerald = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#0F5C4D")
        style = Paint.Style.STROKE
        strokeWidth = 14f
    }
    canvas.drawRect(24f, 24f, width - 24f, height - 24f, borderEmerald)

    val borderGold = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#F59E0B")
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    canvas.drawRect(44f, 44f, width - 44f, height - 44f, borderGold)

    // Header Title
    val headerText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.WHITE
        textSize = 46f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText("ENGLISH सीखो PRO", width / 2f, 92f, headerText)

    val subHeader = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#FDE68A")
        textSize = 26f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText("OFFICIAL CERTIFICATE OF ACHIEVEMENT", width / 2f, 138f, subHeader)

    // Presented to label
    val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#475569")
        textSize = 28f
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText("यह प्रमाणपत्र गर्व के साथ प्रदान किया जाता है:", width / 2f, 255f, labelPaint)

    // Student Name
    val namePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#0F5C4D")
        textSize = 64f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText(progress.studentName, width / 2f, 345f, namePaint)

    // Gold underline under name
    val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#F59E0B")
        strokeWidth = 5f
    }
    canvas.drawLine(width / 2f - 220f, 370f, width / 2f + 220f, 370f, linePaint)

    // Achievement details
    val bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#1E293B")
        textSize = 30f
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText(
        "Rank: ${progress.rankTitleHi()}",
        width / 2f,
        445f,
        bodyPaint
    )
    canvas.drawText(
        "Level: ${progress.level.titleEn}  |  Streak: ${progress.streakDays} Days",
        width / 2f,
        500f,
        bodyPaint
    )

    // Stats Box
    val boxPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#ECFDF5")
    }
    val statRect = RectF(180f, 545f, width - 180f, 645f)
    canvas.drawRoundRect(statRect, 22f, 22f, boxPaint)

    val statPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#0F5C4D")
        textSize = 32f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText(
        "${progress.xp} XP   •   ${progress.coins} Coins   •   ${progress.completedDayNumbers.size}/30 Days",
        width / 2f,
        607f,
        statPaint
    )

    // Footer Date
    val dateStr = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
    val footerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = AndroidColor.parseColor("#64748B")
        textSize = 24f
        textAlign = Paint.Align.CENTER
    }
    canvas.drawText("Issued on $dateStr  •  100% Offline Hindi-to-English Learning App", width / 2f, 720f, footerPaint)

    runCatching {
        val dir = File(context.cacheDir, "certificates").apply { mkdirs() }
        val outFile = File(dir, "english_seekho_certificate.png")
        FileOutputStream(outFile).use { fos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", outFile)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(
                Intent.EXTRA_TEXT,
                "मैं 'English सीखो Pro' ऐप से रोज़ English सीख रहा हूँ! मेरी रैंक: ${progress.rankTitleHi()} (${progress.xp} XP, ${progress.streakDays} दिन की Streak)।"
            )
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "सर्टिफिकेट फोटो शेयर करें"))
    }.onFailure {
        val fallbackIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "मैं 'English सीखो Pro' ऐप से English सीख रहा हूँ! मेरी रैंक: ${progress.rankTitleHi()} (${progress.xp} XP, ${progress.coins} Coins)।"
            )
        }
        context.startActivity(Intent.createChooser(fallbackIntent, "प्रगति शेयर करें"))
    }
}

