package com.cinncinatiai.signinapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val presenter: MainPresenter by lazy {
        MainPresenter()
    }
    private val emailText: EditText by lazy {
        findViewById(R.id.email_text)
    }
    private val passwordText: EditText by lazy {
        findViewById(R.id.password_text)
    }
    private val signinButton: Button by lazy {
        findViewById(R.id.submit_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        presenter.uiState.observe(this) { onUIState(it) }
        signinButton.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            presenter.signInGranted(email,password)

        }
    }

    private fun onUIState(uiState: MainUIState) {
        when (uiState) {

            MainUIState.None -> {}
            is MainUIState.AccessGranted -> {
                switchActivity()
                emailText.text.clear()
                passwordText.text.clear()
            }
            is MainUIState.Error -> Toast.makeText(this, R.string.errorMessage, Toast.LENGTH_LONG).show()
        }

    }

    private fun switchActivity(){
        val switchIntent = Intent(this@MainActivity, Welcome::class.java)
        startActivity(switchIntent)
    }
}