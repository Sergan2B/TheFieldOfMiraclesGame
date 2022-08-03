package com.example.miraclegame.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.miraclegame.domain.model.ScoreItem

class ScoreItemDiffCallback: DiffUtil.ItemCallback<ScoreItem>() {
    override fun areItemsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean {
        return oldItem == newItem
    }
}