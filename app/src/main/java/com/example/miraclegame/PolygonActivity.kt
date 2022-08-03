package com.example.miraclegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivityPolygonBinding


class PolygonActivity : BaseActivity<PolygonViewModel, ActivityPolygonBinding>() {
    override val viewModel: PolygonViewModel by lazy { ViewModelProvider(this)[PolygonViewModel::class.java] }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPolygonBinding {
        return ActivityPolygonBinding.inflate(inflater)
    }
}