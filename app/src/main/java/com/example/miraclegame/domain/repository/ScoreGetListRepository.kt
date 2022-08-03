package com.example.miraclegame.domain.repository

import androidx.lifecycle.LiveData
import com.example.miraclegame.domain.model.ScoreItem

interface ScoreGetListRepository {
    fun getScoreList(): LiveData<List<ScoreItem>>
}