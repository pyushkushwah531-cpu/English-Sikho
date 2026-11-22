package com.englishseekho.pro.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.englishseekho.pro.MainActivity
import com.englishseekho.pro.R
import com.englishseekho.pro.data.CurriculumRepository
import com.englishseekho.pro.data.LocalProgressStore
import java.util.Calendar

class WordOfDayWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val progress = LocalProgressStore(context).load()
        val dayIndex = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % CurriculumRepository.flashcards.size
        val card = CurriculumRepository.flashcards[dayIndex]

        for (widgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_word_of_day)
            views.setTextViewText(R.id.widget_word, "${card.wordEn} (${card.pronunciationHi})")
            views.setTextViewText(R.id.widget_meaning, "${card.meaningHi} · ${card.exampleEn}")
            views.setTextViewText(R.id.widget_streak, "Daily Streak: ${progress.streakDays} Days · ${progress.xp} XP")

            val launchIntent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                launchIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_container, pendingIntent)
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
