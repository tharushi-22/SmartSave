package com.example.labexam_03

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signUp : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Window inset handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        nameInput = findViewById(R.id.hntName)
        emailInput = findViewById(R.id.hntEmail)
        passwordInput = findViewById(R.id.hntPassword)
        confirmPasswordInput = findViewById(R.id.hntConfirmPwd)
        signUpButton = findViewById(R.id.btnSignup)
        loginButton = findViewById(R.id.btnlogin)

        // Sign Up button click
        signUpButton.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, logIn::class.java))
                finish()
            }
        }

        // Login button click
        loginButton.setOnClickListener {
            startActivity(Intent(this, logIn::class.java))
        }
    }

    private fun validateForm(): Boolean {
        val name = nameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (name.isEmpty()) {
            nameInput.error = "Name is required"
            return false
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Valid email is required"
            return false
        }

        if (password.length < 6) {
            passwordInput.error = "Password must be at least 6 characters"
            return false
        }

        if (confirmPassword != password) {
            confirmPasswordInput.error = "Passwords do not match"
            return false
        }

        return true
    }
}
