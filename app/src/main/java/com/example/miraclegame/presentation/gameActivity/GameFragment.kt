package com.example.miraclegame.presentation.gameActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miraclegame.R
import com.example.miraclegame.core.ui.fragments.BaseFragment
import com.example.miraclegame.databinding.FragmentGameBinding

class GameFragment : BaseFragment<FragmentGameBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun inflateVB(inflater: LayoutInflater): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater)
    }
}