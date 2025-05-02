package com.cinncinatiai.signinapplication

class MainPresenter {

    fun signInGranted(email: String, password: String): Boolean {
        if (emailValidation(email) && passwordValidation(password)){
            return true
        } else{
            return false
        }
    }

    fun emailValidation(email: String): Boolean {
        val checkAt: Boolean = "@" in email
        val checkCom: Boolean = ".com" in email
        return (checkAt && checkCom)
    }

    fun passwordValidation(password: String): Boolean {
        if (password.length >= 8) {
            return true
        } else{
            return false
        }
    }

}