package com.example.miraclegame.presentation.settingsActivity

import android.media.AudioManager
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.ViewModelProvider
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivitySettingsBinding

class SettingsActivity : BaseActivity<SettingsViewModel, ActivitySettingsBinding>() {
    override val viewModel: SettingsViewModel by lazy { ViewModelProvider(this)[SettingsViewModel::class.java] }

    override fun initListener() {
        super.initListener()
        audioSettings()
    }

    private fun audioSettings() {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val volControl: SeekBar = binding.seekForSound
        volControl.max = maxVolume
        volControl.progress = curVolume
        volControl.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(arg0: SeekBar) {}
            override fun onStartTrackingTouch(arg0: SeekBar) {}
            override fun onProgressChanged(arg0: SeekBar, arg1: Int, arg2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0)
            }
        })
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySettingsBinding {
        return ActivitySettingsBinding.inflate(inflater)
    }
}