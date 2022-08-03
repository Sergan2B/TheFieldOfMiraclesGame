package com.example.miraclegame.presentation.gameActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.miraclegame.core.ui.activities.BaseViewModel
import com.example.miraclegame.data.repository.ScoreListRepositoryImpl
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.useCase.AddScoreItemUseCase
import com.example.miraclegame.presentation.gameActivity.GameActivity.Companion.questions
import com.example.miraclegame.presentation.gameActivity.GameActivity.Companion.words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class GameViewModel : BaseViewModel() {

    private val repositoryImpl = ScoreListRepositoryImpl()
    private val addScoreItemUseCase = AddScoreItemUseCase(repositoryImpl)

    private val randomWords = Random()
    private var count: Int = 0
    private var score = MutableLiveData<Int>()
    private lateinit var tvResult: String
    private lateinit var tvQuestion: String
    private var checkSt = false

    fun addScoreItem(scoreItem: ScoreItem) {
        viewModelScope.launch(Dispatchers.IO) {
            addScoreItemUseCase.addScoreList(scoreItem)
        }
    }

    fun working(): String {
        if (count == 0) count = randomWords.nextInt(words.size)
        tvResult = questions[count]
        tvQuestion = questions[count]
        return tvQuestion
    }
    //binding.tvQuestion.text = tvQuestion
}