package ru.mobiledevelopment.funnytictactoe.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configurateScreen()
        initMediaPlayer()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<GameMenuFragment>(R.id.fragment_container_view)
            }
        }

        supportFragmentManager.setFragmentResultListener("soundRequest",
            this) { requestKey, bundle ->
            val result = bundle.getBoolean("soundKey")
            if (result) {
                mediaPlayer?.start()
            } else {
                mediaPlayer?.pause()
            }
        }
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.ms)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        mediaPlayer?.setVolume(0.6f, 0.6f);
    }

    private fun configurateScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        supportActionBar?.hide()
    }


    private fun fullScreenCall() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onResume() {
        super.onResume()
        fullScreenCall()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

}

