package com.example.miraclegame.presentation.gameActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.*
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.lifecycle.ViewModelProvider
import com.example.miraclegame.core.extentions.alert
import com.example.miraclegame.core.extentions.showToastShort
import com.example.miraclegame.core.ui.activities.BaseActivity
import com.example.miraclegame.databinding.ActivityGameBinding
import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.presentation.drumActivity.DrumActivity
import com.example.miraclegame.utils.Constant.questionsArray
import com.example.miraclegame.utils.Constant.wordsArray
import java.util.*

class GameActivity : BaseActivity<GameViewModel, ActivityGameBinding>() {

    override val viewModel: GameViewModel by lazy { ViewModelProvider(this)[GameViewModel::class.java] }
    private lateinit var tvResult: String
    private lateinit var tvQuestion: String
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val randomWords = Random()
    private var score: Int = 0
    private var count: Int = 0
    private var checkSt = false

    override fun initView() {
        super.initView()
        checkHelper()
        initArraysToList()
        initViews()
        initLauncher()
    }

    private fun checkHelper() {
        if (checkSt) helperForQuestions()
    }

    private fun helperForQuestions() {
        when (tvResult) {
            "Жупа" -> binding.tvResult.text = "Ж*п*"
            "Чихание" -> binding.tvResult.text = "Ч*ха**е"
            "Правда" -> binding.tvResult.text = "П*ав*а"
            "Гавкает" -> binding.tvResult.text = "Г*в*а**"
            "Сковорода" -> binding.tvResult.text = "Ск*во*о**"
            "Болтливость" -> binding.tvResult.text = "Бо**ли*о**ь"
            "Подросток" -> binding.tvResult.text = "П*д*ос**к"
            "Канализация" -> binding.tvResult.text = "К*н*л*з*ц*я"
            "Авоська" -> binding.tvResult.text = "А**сь*а"
            "Разочаровываться" -> binding.tvResult.text = "Ра*о**ро*ы**ть**"
        }
    }

    private fun initAlert() {
        alert {
            setTitle("Поздравляем")
            setMessage("Вы выиграли в этой игре, хотите еще раз попробовать?")
            setPositiveButton("Новая игра") { _, _ -> recreate() }
            setNeutralButton("Хочу сохранить результат") { _, _ ->
                alert {
                    setTitle("Вы хотите сохранить Результат?")
                    setMessage("Когда вы сохраните, то сможете увидеть свой результат в таблице рекордов")
                    setPositiveButton("Да") { _, _ ->
                        alert {
                            setTitle("Введите свое имя")
                            val input = EditText(context)
                            input.hint = "Ваше имя"
                            setView(input)
                            val inputTextView = input.text
                            setPositiveButton("Сохранить") { _, _ ->
                                viewModel.addScoreItem(ScoreItem(inputTextView.toString(), score))
                                showToastShort(inputTextView.toString())
                            }
                            setNegativeButton("Не сохронять") { al, _ -> al.cancel() }
                        }
                    }
                    setNegativeButton("Нет") { al, _ -> al.cancel() }
                }
            }
            setNegativeButton("Выйти") { _, _ -> finish() }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initLauncher() {
        activityResultLauncher = registerForActivityResult(StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val w: Int
                val data: Int = it.data!!.getIntExtra("Sergan", 0)
                binding.tvResultPoints.text = "$data Очков"
                val newResult: String =
                    binding.tvResultPoints.text.toString().replace(" Очков", "").trim()
                val s: Int = binding.tvAllPoints.text.toString().toInt()
                w = newResult.toInt()
                score = s + w
                binding.tvAllPoints.text = score.toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        if (count == 0) count = randomWords.nextInt(words.size)
        tvResult = words[count]
        tvQuestion = questions[count]
        binding.tvQuestion.text = tvQuestion
        initGame()
        initLogic()
    }

    @SuppressLint("SetTextI18n")
    private fun initGame() {
        when (tvResult.length) {
            4 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXX"
            5 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXX"
            6 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXX"
            7 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXX"
            8 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXX"
            9 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXX"
            10 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXX"
            11 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXX"
            12 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXX"
            13 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXXX"
            14 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXXXX"
            15 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXXXXX"
            16 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXXXXXX"
            17 -> if (binding.tvResult.text === "") binding.tvResult.text = "XXXXXXXXXXXXXXXXX"
        }
    }

    private fun initLogic() {
        val checkText: String = java.lang.String.valueOf(binding.etText.text)
        if (checkText != tvResult) {
            if (checkText.isEmpty()) {
                showToastShort("Введтие текст... ")
            } else {
                showToastShort("Неправильный ответ")
                score -= 50
                binding.tvAllPoints.text = score.toString()
                if (score < -400) alert {
                    setTitle("Сожалеем")
                    setMessage("Вы проиграли в этой игре, хотите еще раз попробовать?")
                    setPositiveButton("Новая игра") { _, _ -> recreate() }
                    setNegativeButton("Выйти") { _, _ -> finish() }
                }
                else if (score > 500) initAlert()
            }
        } else {
            val s: String = tvResult.uppercase(Locale.getDefault()).trim { it <= ' ' }.replace(
                tvResult, binding.etText.text.toString())
            binding.tvResult.text = s
            showToastShort("Вы угадали слово. Вы победили")
            initAlert()
        }
    }

    private fun initArraysToList() {
        words.addAll(wordsArray)
        questions.addAll(questionsArray)
    }

    override fun initListener() = with(binding) {
        btnRounded.visibility = GONE
        btnRounded.setOnClickListener {
            val intent = Intent(this@GameActivity, DrumActivity::class.java)
            val ser = tvAllPoints.text.toString().toInt()
            intent.putExtra("SerganPut", ser)
            btnWord.visibility = VISIBLE
            activityResultLauncher.launch(intent)
        }
        btnWord.setOnClickListener {
            btnRounded.visibility = VISIBLE
            btnWord.visibility = GONE
            initViews()
        }
        buttonHelp.setOnClickListener {
            helperForQuestions()
            buttonHelp.visibility = INVISIBLE
            checkSt = true
        }
        tvHelper.setOnClickListener {
            alert {
                setTitle("Правило игры")
                setMessage("""
    Для победы необходимо больше 500 очков, либо необходимо угадать слово
    Для поражения необходимо меньше -400 очков
    BAN дает -150 очков
    NICE дает 1 очко
                """.trimIndent())
                setPositiveButton("Понятно") { it, _ -> it.cancel() }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityGameBinding {
        return ActivityGameBinding.inflate(inflater)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


    }

    companion object {
        val words: MutableList<String> = ArrayList()
        val questions: MutableList<String> = ArrayList()
    }
}