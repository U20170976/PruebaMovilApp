package com.example.pruebamovilapp.data.repository

import com.example.pruebamovilapp.data.model.User
import com.example.pruebamovilapp.data.database.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}
