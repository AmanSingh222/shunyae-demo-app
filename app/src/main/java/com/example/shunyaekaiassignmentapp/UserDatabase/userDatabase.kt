package com.example.shunyaekaiassignmentapp.UserDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shunyaekaiassignmentapp.Dao.UserDao
import com.example.shunyaekaiassignmentapp.Model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_db"
            ).build().also { instance = it }
        }
    }
}
