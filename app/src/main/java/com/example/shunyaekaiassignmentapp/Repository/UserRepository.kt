package com.example.shunyaekaiassignmentapp.Repository

import com.example.shunyaekaiassignmentapp.Dao.UserDao
import com.example.shunyaekaiassignmentapp.Model.User

class UserRepository(private val userDao: UserDao) {
    fun getAllUsers() = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}
