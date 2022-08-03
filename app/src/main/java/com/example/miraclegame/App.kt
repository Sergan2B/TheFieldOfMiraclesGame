package com.example.miraclegame

import android.app.Application
import androidx.room.Room
import com.example.miraclegame.data.local.AppDataBase
import com.example.miraclegame.di.DaggerNewComponent
import com.example.miraclegame.domain.model.ScoreItem
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var scoreItem: ScoreItem

    init {
        DaggerNewComponent.create().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "dataBase")
            .fallbackToDestructiveMigration().build()
    }

    companion object {
        lateinit var db: AppDataBase
    }
}