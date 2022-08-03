package com.example.miraclegame.di

import android.app.Application
import com.example.miraclegame.domain.model.NameModule
import dagger.Component

@Component(modules = [NameModule::class])
interface NewComponent {
    fun inject(app: Application)
}