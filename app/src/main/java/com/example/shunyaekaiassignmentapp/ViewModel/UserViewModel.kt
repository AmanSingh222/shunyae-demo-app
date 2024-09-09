package com.example.shunyaekaiassignmentapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shunyaekaiassignmentapp.Model.User
import com.example.shunyaekaiassignmentapp.Repository.UserRepository
import com.example.shunyaekaiassignmentapp.UserDatabase.UserDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.invoke(application).userDao()
    private val repository: UserRepository = UserRepository(userDao)
    val users = repository.getAllUsers()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }
}
