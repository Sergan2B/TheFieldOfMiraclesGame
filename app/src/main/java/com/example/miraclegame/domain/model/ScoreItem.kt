package com.example.miraclegame.domain.model

import javax.inject.Inject

data class ScoreItem @Inject constructor(
    val name: String,
    val score: Int,
    val id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
