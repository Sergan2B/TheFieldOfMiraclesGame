package com.example.miraclegame.utils

import com.example.miraclegame.data.model.ScoreDBModel
import com.example.miraclegame.domain.model.ScoreItem

class ScoreMapper {

    fun mapEntityToDBModel(scoreItem: ScoreItem) = ScoreDBModel(
        id = scoreItem.id,
        name = scoreItem.name,
        score = scoreItem.score,
    )

    fun mapDBModelToEntity(scoreDBModel: ScoreDBModel) = ScoreItem(
        id = scoreDBModel.id,
        name = scoreDBModel.name,
        score = scoreDBModel.score,
    )

    fun mapListDBModelToListEntity(scoreListDBModel: List<ScoreDBModel>) = scoreListDBModel.map {
        mapDBModelToEntity(it)
    }
}