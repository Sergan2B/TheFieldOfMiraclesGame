package com.example.miraclegame.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.miraclegame.data.model.ScoreDBModel

@Dao
interface ScoreItemDao {

    @Query("SELECT * FROM score_item")
    fun getAll(): LiveData<List<ScoreDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addModelScore(modelScoreDBModel: ScoreDBModel)

    @Delete
     fun deleteModelScore(modelScoreDBModel: ScoreDBModel)

    @Update
     fun editModelScore(modelScoreDBModel: ScoreDBModel)
}