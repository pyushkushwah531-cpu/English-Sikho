package com.englishseekho.pro.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.englishseekho.pro.data.LocalProgressStore

class DailyReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val progress = LocalProgressStore(context).load()
        if (progress.notificationsEnabled) {
            NotificationHelper.showDailyReminderNotification(context)
        }
    }
}
