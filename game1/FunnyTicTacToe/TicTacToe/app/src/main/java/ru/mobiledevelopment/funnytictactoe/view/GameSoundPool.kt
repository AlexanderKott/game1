package ru.mobiledevelopment.funnytictactoe.view

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import ru.mobiledevelopment.funnytictactoe.R


class GameSoundPool(private val context: Context) {
    private var soundPool: SoundPool
    private var audioAttributes: AudioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    init {

        soundPool = SoundPool.Builder()
            .setMaxStreams(7)
            .setAudioAttributes(audioAttributes)
            .build()

        initSounds()
    }

    private var win: Int = 0
    private var firstPlayer: Int = 0
    private var secondPlayer: Int = 0
    private var wtf: Int = 0
    private var clearField: Int = 0

    private var leftVolume: Float = 0.0F
    private var rightVolume: Float = 0.0F

    private fun initSounds() {
        win = soundPool.load(context, R.raw.win, 1)
        firstPlayer = soundPool.load(context, R.raw.player1, 1)
        secondPlayer = soundPool.load(context, R.raw.player2, 1)
        wtf = soundPool.load(context, R.raw.switch_006, 1)
        clearField = soundPool.load(context, R.raw.clearfield, 1)

        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        val curVolume = audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        leftVolume = curVolume / maxVolume
        rightVolume = curVolume / maxVolume

    }

    fun playWin() {
        soundPool.play(win, leftVolume, rightVolume, 1, 0, 1F);
    }

    fun playFirstPlayer() {
        soundPool.play(firstPlayer, leftVolume, rightVolume, 1, 0, 1f);
    }

    fun playSecondPlayer() {
        soundPool.play(secondPlayer, leftVolume, rightVolume, 1, 0, 1f);
    }

    fun playCheating() {
        soundPool.play(wtf, leftVolume, rightVolume, 1, 0, 1F);
    }

    fun playClearField() {
        soundPool.play(clearField, leftVolume, rightVolume, 1, 0, 1F);
    }

}