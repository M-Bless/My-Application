package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    // Declare views
    private lateinit var btnSignUp: Button
    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editCPassword: EditText
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        btnSignUp = findViewById(R.id.btnSignUp)
        editUsername = findViewById(R.id.editUsername)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editCPassword = findViewById(R.id.editCPassword)
        tvLogin = findViewById(R.id.tvLogin)

        // Handle Sign Up button click
        btnSignUp.setOnClickListener {
            val username = editUsername.text.trim().toString()
            val email = editEmail.text.trim().toString()
            val password = editPassword.text.trim().toString()
            val cPassword = editCPassword.text.trim().toString()

            // Validate inputs
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && cPassword.isNotEmpty()) {
                if (password == cPassword) {
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_LONG).show()
                    // Add sign-up logic (e.g., store user data, authenticate, etc.)
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }

        // Handle login TextView click
        tvLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
