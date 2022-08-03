package com.example.miraclegame.presentation.leaderboardActivity

import androidx.lifecycle.viewModelScope
import com.example.miraclegame.core.ui.activities.BaseViewModel
import com.example.miraclegame.data.repository.ScoreListRepositoryImpl
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.useCase.AddScoreItemUseCase
import com.example.miraclegame.domain.useCase.DeleteScoreItemUseCase
import com.example.miraclegame.domain.useCase.GetScoreItemUseCase
import com.example.miraclegame.domain.useCase.GetScoreListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeaderboardViewModel : BaseViewModel() {
    private val repositoryImpl = ScoreListRepositoryImpl()
    private val deleteScoreItemUseCase = DeleteScoreItemUseCase(repositoryImpl)
    private val getScoreItemUseCase = GetScoreItemUseCase(repositoryImpl)
    private val getScoreListUseCase = GetScoreListUseCase(repositoryImpl)

    val scoreListLD = getScoreListUseCase.getScoreList()

    fun deleteScoreItem(scoreItem: ScoreItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteScoreItemUseCase.deleteScoreItem(scoreItem)
        }
    }

    fun getScoreItem(scoreItem: Int): ScoreItem? {
        var data: ScoreItem? = null
        viewModelScope.launch(Dispatchers.IO) {
            data = getScoreItemUseCase.getScoreItem(scoreItem)
        }
        return data
    }
}