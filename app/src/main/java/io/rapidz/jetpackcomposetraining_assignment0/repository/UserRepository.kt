package io.rapidz.jetpackcomposetraining_assignment0.repository

import io.rapidz.jetpackcomposetraining_assignment0.dao.UserDao
import io.rapidz.jetpackcomposetraining_assignment0.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    suspend fun getUserByUsername(username: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserByUsername(username)
        }
    }

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }
}