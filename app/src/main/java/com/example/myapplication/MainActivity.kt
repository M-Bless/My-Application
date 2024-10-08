package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Connect to activity_main.xml
        setContentView(R.layout.activity_main)

        // Get references to the views
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val edUsername: EditText = findViewById(R.id.edUsername)
        val edPassword: EditText = findViewById(R.id.edPassword)
        val tvSignUp: TextView = findViewById(R.id.textview)

        // Handle Login Button Click
        btnLogin.setOnClickListener {
            val username = edUsername.text.trim().toString()
            val password = edPassword.text.trim().toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Input provided", Toast.LENGTH_LONG).show()
                // Perform login logic here (e.g., authenticate the user)
            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }

        // Handle Signup TextView Click
        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
