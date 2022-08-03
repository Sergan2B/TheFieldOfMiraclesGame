package com.example.miraclegame.presentation.interfaceActivity

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.miraclegame.PolygonActivity
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivityInterfaceBinding
import com.example.miraclegame.presentation.gameActivity.GameActivity
import com.example.miraclegame.presentation.leaderboardActivity.LeaderboardActivity
import com.example.miraclegame.presentation.settingsActivity.SettingsActivity

class InterfaceActivity : BaseActivity<InterfaceViewModel, ActivityInterfaceBinding>() {
    override val viewModel: InterfaceViewModel by lazy { ViewModelProvider(this)[InterfaceViewModel::class.java] }

    override fun initListener() = with(binding) {
        ivPolygon.setOnClickListener {
            intent = Intent(this@InterfaceActivity, PolygonActivity::class.java)
            startActivity(intent)
        }
        startGame.setOnClickListener {
            intent = Intent(this@InterfaceActivity, GameActivity::class.java)
            startActivity(intent)
        }
        settings.setOnClickListener {
            intent = Intent(this@InterfaceActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        leaderboard.setOnClickListener {
            intent = Intent(this@InterfaceActivity, LeaderboardActivity::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener { finish() }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityInterfaceBinding {
        return ActivityInterfaceBinding.inflate(inflater)
    }
}