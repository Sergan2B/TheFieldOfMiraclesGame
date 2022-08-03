package com.example.miraclegame.presentation.drumActivity

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.ViewModelProvider
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivityDrumBinding
import com.example.miraclegame.utils.Constant.numbersArray
import java.util.*

class DrumActivity : BaseActivity<DrumViewModel, ActivityDrumBinding>() {
    override val viewModel: DrumViewModel by lazy { ViewModelProvider(this)[DrumViewModel::class.java] }
    private lateinit var random: Random
    private var oldCount = 0
    private var count = 0
    private val factor = 4.5f //Значение взято из серединного центра нашего барабана в момент крутки

    override fun initView() {
        super.initView()
        supportActionBar?.hide()
        initial()
        getPoint()

    }

    private fun getPoint() {
        val intention = intent
        val ser = intention.getIntExtra("SerganPut", 0)
        val str = ser.toString()
        binding.tvAllPoints.text = str
    }

    private fun initial() {
        random = Random()
        binding.buttonOk.visibility = View.GONE
    }

    override fun initListener() {
        binding.buttonRandom.setOnClickListener {
            oldCount = count % 360 //расчет оборота нашего круга
            count = random.nextInt(360) + 720 //выбор рандомного значение из нашего круга
            val rotateAnimation = RotateAnimation(oldCount.toFloat(), count.toFloat(),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f) //Создания анимации вращения
            rotateAnimation.duration = 3600 //длительность врашения
            rotateAnimation.fillAfter = true //фильтер анимации
            rotateAnimation.interpolator = DecelerateInterpolator()
            rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    binding.tvResult.text = ""
                }

                override fun onAnimationEnd(animation: Animation) { //родительский метод конца анимации
                    binding.tvResult.text = getResult(360 - count % 360)
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            binding.imageView.startAnimation(rotateAnimation)
            binding.buttonRandom.visibility = View.GONE
        }

        binding.buttonOk.setOnClickListener {
            val ser: String = binding.tvResult.text.toString()
            val s: Int
            when (ser) {
                "250 очков" -> {
                    s = 250
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "150 очков" -> {
                    s = 150
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "100 очков" -> {
                    s = 100
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "50 очков" -> {
                    s = 50
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "30 очков" -> {
                    s = 30
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "25 очков" -> {
                    s = 25
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "15 очков" -> {
                    s = 15
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "10 очков" -> {
                    s = 10
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "5 очков" -> {
                    s = 5
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "0 очков" -> {
                    s = 0
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "X2 к очкам" -> {
                    s = binding.tvAllPoints.text.toString().toInt() * 2
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "BAN" -> {
                    s = -150
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
                "NICE" -> {
                    s = 1
                    setResult(RESULT_OK, intent.putExtra("Sergan", s))
                }
            }
            finish()
        }
    }

    private fun getResult(countRet: Int): String {
        var text = "" //пустой текст
        var factorX = 1 //икс горизонталь
        var factorY = 3 //игрик вертикаль
        for (i in 0..39) { //Цикл, у нас в барабане 40 вариантов, поэтому 40 раз будем проходить
            if (countRet >= factor * factorX && countRet < factor * factorY) { //проверка
                text = numbersArray[i] //Устанавливаем то что получили
            }
            factorX += 2 //Равномерное движение в направлении
            factorY += 2 //Равномерное движение в направлении
        }
        //Проверка, на круг и его вращение, либо нулевое значение.
        if (countRet >= factor * 81 && countRet < 360 || countRet >= 0 && countRet < factor * 1) text =
            numbersArray[numbersArray.size - 1] //устанавливаем то что получили
        binding.buttonOk.visibility = View.VISIBLE //делаем кнопку выхода видимой
        return text //возвращяем текст
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDrumBinding {
        return ActivityDrumBinding.inflate(inflater)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return keyCode == KeyEvent.KEYCODE_BACK
    }
}