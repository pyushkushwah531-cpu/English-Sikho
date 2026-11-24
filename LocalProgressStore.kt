package com.englishseekho.pro.data

import android.content.Context
import org.json.JSONArray
import java.util.Calendar

data class UserProgress(
    val studentName: String = "विद्यार्थी",
    val xp: Int = 40,
    val coins: Int = 25,
    val streakDays: Int = 1,
    val lastActiveDateKey: Int = 0,
    val level: StudentLevel = StudentLevel.ZERO,
    val onboardingDone: Boolean = false,
    val completedTaskIds: Set<String> = emptySet(),
    val completedDayNumbers: Set<Int> = emptySet(),
    val masteredWordIds: Set<String> = emptySet(),
    val reviewWordIds: Set<String> = emptySet(),
    val unlockedBadges: Set<String> = emptySet(),
    val puzzleLevelIndex: Int = 0,
    val puzzleSolvedCount: Int = 0,
    val wordMatchBatchIndex: Int = 0,
    val bossQuestionIndex: Int = 0,
    val bossScoreCount: Int = 0,
    val listeningLevelIndex: Int = 0,
    val listeningScoreCount: Int = 0,
    val soundEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val reminderHour: Int = 8,
    val geminiApiKey: String = ""
) {
    fun rankTitleHi(): String = when {
        xp >= 1000 -> "Fluent Champion (विजेता)"
        xp >= 600 -> "Confident Speaker (आत्मविश्वासी वक्ता)"
        xp >= 300 -> "Grammar Builder (वाक्य निर्माता)"
        xp >= 120 -> "Word Explorer (शब्द खोजी)"
        else -> "Beginner Learner (शुरुआती छात्र)"
    }
}

class LocalProgressStore(context: Context) {
    private val prefs = context.getSharedPreferences("english_seekho_pro_prefs", Context.MODE_PRIVATE)

    fun load(): UserProgress {
        val levelName = prefs.getString("level", StudentLevel.ZERO.name) ?: StudentLevel.ZERO.name
        val level = runCatching { StudentLevel.valueOf(levelName) }.getOrDefault(StudentLevel.ZERO)
        val raw = UserProgress(
            studentName = prefs.getString("student_name", "विद्यार्थी") ?: "विद्यार्थी",
            xp = prefs.getInt("xp", 40),
            coins = prefs.getInt("coins", 25),
            streakDays = prefs.getInt("streak_days", 1),
            lastActiveDateKey = prefs.getInt("last_active_date_key", 0),
            level = level,
            onboardingDone = prefs.getBoolean("onboarding_done", false),
            completedTaskIds = readSet("completed_tasks"),
            completedDayNumbers = readIntSet("completed_days"),
            masteredWordIds = readSet("mastered_words"),
            reviewWordIds = readSet("review_words"),
            unlockedBadges = readSet("unlocked_badges"),
            puzzleLevelIndex = prefs.getInt("puzzle_level_index", 0),
            puzzleSolvedCount = prefs.getInt("puzzle_solved_count", 0),
            wordMatchBatchIndex = prefs.getInt("word_match_batch_index", 0),
            bossQuestionIndex = prefs.getInt("boss_question_index", 0),
            bossScoreCount = prefs.getInt("boss_score_count", 0),
            listeningLevelIndex = prefs.getInt("listening_level_index", 0),
            listeningScoreCount = prefs.getInt("listening_score_count", 0),
            soundEnabled = prefs.getBoolean("sound_enabled", true),
            vibrationEnabled = prefs.getBoolean("vibration_enabled", true),
            notificationsEnabled = prefs.getBoolean("notifications_enabled", true),
            reminderHour = prefs.getInt("reminder_hour", 8),
            geminiApiKey = prefs.getString("gemini_api_key", "") ?: ""
        )
        return syncDailyStreak(raw)
    }

    fun syncDailyStreak(current: UserProgress): UserProgress {
        val cal = Calendar.getInstance()
        val todayKey = dateKeyOf(cal)
        val yesterdayCal = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
        val yesterdayKey = dateKeyOf(yesterdayCal)

        if (current.lastActiveDateKey == todayKey) return current

        val updated = when (current.lastActiveDateKey) {
            0 -> current.copy(streakDays = current.streakDays.coerceAtLeast(1), lastActiveDateKey = todayKey)
            yesterdayKey -> current.copy(streakDays = current.streakDays + 1, lastActiveDateKey = todayKey)
            else -> current.copy(streakDays = 1, lastActiveDateKey = todayKey)
        }
        save(updated)
        return updated
    }

    private fun dateKeyOf(cal: Calendar): Int {
        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH) + 1
        val d = cal.get(Calendar.DAY_OF_MONTH)
        return y * 10000 + m * 100 + d
    }

    fun save(progress: UserProgress) {
        prefs.edit()
            .putString("student_name", progress.studentName)
            .putInt("xp", progress.xp)
            .putInt("coins", progress.coins)
            .putInt("streak_days", progress.streakDays)
            .putInt("last_active_date_key", progress.lastActiveDateKey)
            .putString("level", progress.level.name)
            .putBoolean("onboarding_done", progress.onboardingDone)
            .putString("completed_tasks", JSONArray(progress.completedTaskIds.toList()).toString())
            .putString("completed_days", JSONArray(progress.completedDayNumbers.toList()).toString())
            .putString("mastered_words", JSONArray(progress.masteredWordIds.toList()).toString())
            .putString("review_words", JSONArray(progress.reviewWordIds.toList()).toString())
            .putString("unlocked_badges", JSONArray(progress.unlockedBadges.toList()).toString())
            .putInt("puzzle_level_index", progress.puzzleLevelIndex)
            .putInt("puzzle_solved_count", progress.puzzleSolvedCount)
            .putInt("word_match_batch_index", progress.wordMatchBatchIndex)
            .putInt("boss_question_index", progress.bossQuestionIndex)
            .putInt("boss_score_count", progress.bossScoreCount)
            .putInt("listening_level_index", progress.listeningLevelIndex)
            .putInt("listening_score_count", progress.listeningScoreCount)
            .putBoolean("sound_enabled", progress.soundEnabled)
            .putBoolean("vibration_enabled", progress.vibrationEnabled)
            .putBoolean("notifications_enabled", progress.notificationsEnabled)
            .putInt("reminder_hour", progress.reminderHour)
            .putString("gemini_api_key", progress.geminiApiKey)
            .apply()
    }

    private fun readSet(key: String): Set<String> {
        val raw = prefs.getString(key, "[]") ?: "[]"
        val result = mutableSetOf<String>()
        runCatching {
            val array = JSONArray(raw)
            for (i in 0 until array.length()) {
                result.add(array.getString(i))
            }
        }
        return result
    }

    private fun readIntSet(key: String): Set<Int> {
        val raw = prefs.getString(key, "[]") ?: "[]"
        val result = mutableSetOf<Int>()
        runCatching {
            val array = JSONArray(raw)
            for (i in 0 until array.length()) {
                result.add(array.getInt(i))
            }
        }
        return result
    }
}
