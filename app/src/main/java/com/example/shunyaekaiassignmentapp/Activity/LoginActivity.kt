package com.example.shunyaekaiassignmentapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.shunyaekaiassignmentapp.MainActivity
import com.example.shunyaekaiassignmentapp.R
import com.example.shunyaekaiassignmentapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        binding.loginButton.setOnClickListener {
            if (binding.emailEditText.text.toString().isEmpty() && binding.passwordEditText.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()

            }else{
                loginUser(binding.emailEditText.text.toString(),binding.passwordEditText.text.toString())

            }
        }
    }


    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this,UserListActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Login successful", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
