package com.example.miraclegame

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.WindowMetrics
import android.widget.MediaController
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.miraclegame.core.ui.fragments.BaseFragment
import com.example.miraclegame.databinding.FragmentPolygonBinding


class PolygonFragment : BaseFragment<FragmentPolygonBinding>() {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var check = false
    private var videoData: Uri? = null

    override fun initView() {
        val mediaController = MediaController(requireContext())
        val displayM: WindowMetrics = activity?.windowManager!!.maximumWindowMetrics
        val height1 = displayM.bounds.height()
        val width1 = displayM.bounds.width()
        mediaController.setAnchorView(binding.vvBack)
        mediaController.rootView
        binding.vvBack.setNewDimension(width1, height1)
        binding.vvBack.setMediaController(mediaController)
        if (!check) {
            initMediaOffline()
            binding.vvBack.setOnPreparedListener {
                it.isLooping = true
            }
            binding.vvBack.setOnCompletionListener {
                initMediaOffline()
            }
        }
        initLauncher()
    }

    override fun initListener() {
        super.initListener()
        binding.btnVideoGallery.setOnClickListener {
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_PICK
            //intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                videoData = it.data?.data
                if (videoData != null) {
                    check = true
                    initMedia(videoData!!)
                }
            }
        }
    }

    private fun initMediaOffline() = with(binding.vvBack) {
        val pack = activity?.packageName
        val offline = Uri.parse("android.resource://$pack/${R.raw.v_marci}")
        this.setVideoURI(offline)
        this.start()
    }

    private fun initMedia(video: Uri) = with(binding.vvBack) {
        this.setVideoURI(video)
        this.start()
    }

    override fun inflateVB(inflater: LayoutInflater): FragmentPolygonBinding {
        return FragmentPolygonBinding.inflate(inflater)
    }
}