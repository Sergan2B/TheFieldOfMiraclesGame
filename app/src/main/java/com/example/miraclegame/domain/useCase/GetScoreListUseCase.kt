package com.example.miraclegame.domain.useCase

import androidx.lifecycle.LiveData
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.ScoreGetListRepository

class GetScoreListUseCase(private val scoreListRepository: ScoreGetListRepository) {
    fun getScoreList(): LiveData<List<ScoreItem>> {
        return scoreListRepository.getScoreList()
    }
}