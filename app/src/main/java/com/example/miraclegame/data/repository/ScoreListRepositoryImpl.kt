package com.example.miraclegame.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.miraclegame.App
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.*
import com.example.miraclegame.utils.ScoreMapper

class ScoreListRepositoryImpl : ScoreAddRepository, ScoreDeleteRepository, ScoreEditRepository,
    ScoreGetRepository, ScoreGetListRepository {

    private val shopList =
        sortedSetOf<ScoreItem>({ element1, element2 -> element1.id.compareTo(element2.id) })
    private val mapper = ScoreMapper()

    override suspend fun addScoreItem(scoreItem: ScoreItem) {
        App.db.scoreDao().addModelScore(mapper.mapEntityToDBModel(scoreItem))
    }

    override suspend fun deleteScoreItem(scoreItem: ScoreItem) {
        App.db.scoreDao().deleteModelScore(mapper.mapEntityToDBModel(scoreItem))
    }

    override suspend fun editScoreItem(scoreItem: ScoreItem) {
        App.db.scoreDao().editModelScore(mapper.mapEntityToDBModel(scoreItem))
    }

    override suspend fun getScoreItem(scoreItemId: Int): ScoreItem {
        return shopList.find { it.id == scoreItemId }
            ?: throw RuntimeException("Element with ID $scoreItemId not found")
    }

    override fun getScoreList(): LiveData<List<ScoreItem>> = Transformations.map(
        App.db.scoreDao().getAll()
    ) {
        mapper.mapListDBModelToListEntity(it)
    }
}