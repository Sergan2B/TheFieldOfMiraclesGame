package com.example.miraclegame.domain.useCase

import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.ScoreAddRepository

class AddScoreItemUseCase(private val scoreListRepository: ScoreAddRepository) {
    suspend fun addScoreList(scoreItem: ScoreItem) {
        scoreListRepository.addScoreItem(scoreItem)
    }
}