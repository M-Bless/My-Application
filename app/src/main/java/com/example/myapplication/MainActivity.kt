package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


private lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Connect to activity_main.xml
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Get references to the views
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val EditTextEmail: EditText = findViewById(R.id.edUsername)
        val PasswordEditText: EditText = findViewById(R.id.edPassword)
        val TextViewSignUp: TextView = findViewById(R.id.textview)

        // Handle Login Button Click
        btnLogin.setOnClickListener {
            val email= EditTextEmail.text.trim().toString()
            val password = PasswordEditText.text.trim().toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password )

            } else {
                Toast.makeText(
                    baseContext,
                    "Please fill in both username and password.",
                    Toast.LENGTH_SHORT
                ).show()

            }


            }
        // Handle Signup TextView Click
        TextViewSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        // TODO: Implement what should happen after a successful sign-in
        // For example, navigate to another activity or display user information
        if (user != null) {
            // User is signed in, navigate to the main activity, etc.
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                baseContext,
                "Congratulations you have signed in successfully!",
                Toast.LENGTH_SHORT
            ).show()
            finish() //to prevent going back
        } else {
            // User is not signed in, handle accordingly
            Toast.makeText(
                baseContext,
                "Sign in failed, check your credentials!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    companion object {
        private const val TAG = "SignIn"
    }
}


