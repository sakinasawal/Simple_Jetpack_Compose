package io.rapidz.jetpackcomposetraining_assignment0.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.rapidz.jetpackcomposetraining_assignment0.dao.UserDao
import io.rapidz.jetpackcomposetraining_assignment0.data.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}