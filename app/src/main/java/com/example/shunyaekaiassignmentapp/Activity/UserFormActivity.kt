package com.example.shunyaekaiassignmentapp.Activity

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shunyaekaiassignmentapp.Model.User
import com.example.shunyaekaiassignmentapp.ViewModel.UserViewModel
import com.example.shunyaekaiassignmentapp.databinding.ActivityUserFormBinding

class UserFormActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    lateinit var binding: ActivityUserFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(name = name, email = email, password = password)
                viewModel.insert(user)
                finish()
            }
        }

    }
}
