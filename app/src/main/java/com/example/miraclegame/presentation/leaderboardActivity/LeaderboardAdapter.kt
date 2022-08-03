package com.example.miraclegame.presentation.leaderboardActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.miraclegame.R
import com.example.miraclegame.databinding.ItemLeaderboardBinding
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.utils.ScoreItemDiffCallback

class LeaderboardAdapter :
    ListAdapter<ScoreItem, LeaderboardAdapter.ViewHolder>(ScoreItemDiffCallback()) {

    var onScoreItemClickListener: ((ScoreItem) -> Unit)? = null
    var onScoreItemLongClickListener: ((ScoreItem) -> Unit)? = null

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemLeaderboardBinding.bind(item)
        fun onBind(score: ScoreItem) = with(binding) {
            val scoreItem = getItem(absoluteAdapterPosition)
            tvName.text = score.name
            tvScore.text = score.score.toString() + " очков"

            itemView.setOnClickListener {
                onScoreItemClickListener?.invoke(scoreItem)
            }
            itemView.setOnLongClickListener {
                onScoreItemLongClickListener?.invoke(scoreItem)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}