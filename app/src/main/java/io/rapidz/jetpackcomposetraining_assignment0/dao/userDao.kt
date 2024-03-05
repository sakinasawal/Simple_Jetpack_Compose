package io.rapidz.jetpackcomposetraining_assignment0.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.rapidz.jetpackcomposetraining_assignment0.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}