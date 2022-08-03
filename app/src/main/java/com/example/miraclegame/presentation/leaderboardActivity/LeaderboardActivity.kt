package com.example.miraclegame.presentation.leaderboardActivity

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miraclegame.core.extentions.showToastShort
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : BaseActivity<LeaderboardViewModel, ActivityLeaderboardBinding>() {
    override val viewModel: LeaderboardViewModel by lazy { ViewModelProvider(this)[LeaderboardViewModel::class.java] }
    private lateinit var adapter: LeaderboardAdapter


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityLeaderboardBinding {
        return ActivityLeaderboardBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()
        initViewModel()
        initRecyclerView()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.scoreListLD.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun initListeners() {
        adapter.onScoreItemClickListener = {
            viewModel.deleteScoreItem(it)
        }

        adapter.onScoreItemLongClickListener = {
            showToastShort(it.id.toString().trim())
        }
    }

    private fun initRecyclerView() {
        adapter = LeaderboardAdapter()
        binding.rcScoreboard.layoutManager = LinearLayoutManager(this@LeaderboardActivity)
        binding.rcScoreboard.adapter = adapter
        setUpSwipeListener()
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading
    }

    private fun setUpSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteScoreItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rcScoreboard)
    }
}