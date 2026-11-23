package com.englishseekho.pro.notifications

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.englishseekho.pro.MainActivity
import com.englishseekho.pro.R
import java.util.Calendar

object NotificationHelper {
    private const val CHANNEL_TASK = "english_seekho_tasks"
    private const val CHANNEL_DAILY = "english_seekho_daily"

    fun ensureChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val taskChannel = NotificationChannel(
                CHANNEL_TASK,
                "टास्क व गेम रिवॉर्ड्स",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "टास्क पूरा होने और रैंक अप होने की सूचना"
            }
            val dailyChannel = NotificationChannel(
                CHANNEL_DAILY,
                "दैनिक अभ्यास रिमाइंडर",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "रोज़ाना 10-मिनट English अभ्यास की याद दिलाने के लिए"
            }
            nm.createNotificationChannel(taskChannel)
            nm.createNotificationChannel(dailyChannel)
        }
    }

    fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun showTaskCompletedNotification(
        context: Context,
        title: String,
        message: String
    ) {
        if (!hasNotificationPermission(context)) return
        ensureChannels(context)

        val openIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pending = PendingIntent.getActivity(
            context,
            101,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_TASK)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setAutoCancel(true)
            .setContentIntent(pending)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify((System.currentTimeMillis() % 10000).toInt(), notification)
    }

    fun showDailyReminderNotification(context: Context) {
        if (!hasNotificationPermission(context)) return
        ensureChannels(context)

        val openIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pending = PendingIntent.getActivity(
            context,
            202,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_DAILY)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("English सीखो Pro · आज का 10-मिनट लक्ष्य")
            .setContentText("आज के 5 नए शब्द, 1 कहानी और Sentence Builder गेम आपका इंतज़ार कर रहे हैं!")
            .setAutoCancel(true)
            .setContentIntent(pending)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(2026, notification)
    }

    fun scheduleDailyReminder(context: Context, enabled: Boolean, hourOfDay: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, DailyReminderReceiver::class.java)
        val pending = PendingIntent.getBroadcast(
            context,
            303,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (!enabled) {
            alarmManager.cancel(pending)
            return
        }

        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            cal.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pending
        )
    }
}
