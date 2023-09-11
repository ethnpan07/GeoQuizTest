package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(
    private val savedStateHandle: SavedStateHandle,

) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_sky, true),
        Question(R.string.question_shark, false),
        Question(R.string.question_population, false),
        Question(R.string.question_apple, false),
        Question(R.string.question_sleep, false),
        Question(R.string.question_emus, true)
    )
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

     var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        if(currentIndex == 0){
            currentIndex = 4
        } else {
            currentIndex = (currentIndex - 1)
        }
    }

}