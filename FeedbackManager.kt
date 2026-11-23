package com.englishseekho.pro.services

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class FeedbackManager(private val context: Context) {

    private var toneGenerator: ToneGenerator? = runCatching {
        ToneGenerator(AudioManager.STREAM_MUSIC, 85)
    }.getOrNull()

    @Suppress("DEPRECATION")
    private fun getVibrator(): Vibrator? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vm?.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    @Suppress("DEPRECATION")
    private fun vibratePattern(timings: LongArray) {
        val vibrator = getVibrator() ?: return
        if (!vibrator.hasVibrator()) return
        runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(timings, -1))
            } else {
                vibrator.vibrate(timings, -1)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun vibrateOnce(millis: Long) {
        val vibrator = getVibrator() ?: return
        if (!vibrator.hasVibrator()) return
        runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(millis)
            }
        }
    }

    fun playCorrect(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (soundEnabled) {
            runCatching {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 160)
            }
        }
        if (vibrationEnabled) {
            vibrateOnce(45L)
        }
    }

    fun playWrong(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (soundEnabled) {
            runCatching {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 260)
            }
        }
        if (vibrationEnabled) {
            vibratePattern(longArrayOf(0L, 65L, 55L, 85L))
        }
    }

    fun playCelebration(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (soundEnabled) {
            runCatching {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 280)
            }
        }
        if (vibrationEnabled) {
            vibratePattern(longArrayOf(0L, 50L, 45L, 90L))
        }
    }

    fun release() {
        runCatching { toneGenerator?.release() }
        toneGenerator = null
    }
}
