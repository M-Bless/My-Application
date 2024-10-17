package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

private lateinit var auth: FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        // Implement what you need to do when reloading user data/UI
    }

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

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

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
                    // New password strength validation
                    if (password.length < 6) {
                        Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    // Call the method to create a user
                    createAccount(email, password)
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

    // This method creates a new user with Firebase Authentication
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    task.exception?.let {
                        when (it) {
                            is FirebaseAuthUserCollisionException -> {
                                Toast.makeText(baseContext, "Email already in use. Please try a different email.", Toast.LENGTH_SHORT).show()
                            }
                            is FirebaseAuthWeakPasswordException -> {
                                Toast.makeText(baseContext, "Password is too weak. Please choose a stronger password.", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(baseContext, "Authentication failed: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    updateUI(null)
                }
            }
    }

    // Update the UI according to the signed-in user's status
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in, navigate to the dashboard activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity
        } else {
            // User is not signed in, handle accordingly
            Toast.makeText(baseContext, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "SignUp"
    }
}
