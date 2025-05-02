package com.cinncinatiai.signinapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainPresenter {

    private val minimumPasswordLength = 8
    private val _uiState = MutableLiveData<MainUIState>(MainUIState.None)
    val uiState: LiveData<MainUIState> = _uiState

    fun signInGranted(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            _uiState.postValue(MainUIState.AccessGranted(true))
        } else {
            _uiState.postValue(MainUIState.Error(R.string.errorMessage.toString()))
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val checkAt: Boolean = "@" in email
        val checkCom: Boolean = ".com" in email
        return (checkAt && checkCom)
    }

    private fun isPasswordValid(password: String) = password.length >= minimumPasswordLength


}

sealed class MainUIState {
    object None : MainUIState()
    data class AccessGranted(val access: Boolean) : MainUIState()
    //Data holds data
    data class Error(val message: String) : MainUIState()
}