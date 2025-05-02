package com.cinncinatiai.signinapplication

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


    private val userText: EditText by lazy {
        findViewById(R.id.user_text)
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        signinButton.setOnClickListener {
            val email: String = userText.text.toString()
            val password: String = passwordText.text.toString()
            Log.i("MainActivity", "${email} and ${password}")

            if (presenter.signInGranted(email, password)) {
                TODO()
            } else {
                Toast.makeText(
                    this,
                    "Give me a valid user and password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}