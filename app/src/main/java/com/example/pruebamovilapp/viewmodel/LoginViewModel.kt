package com.example.pruebamovilapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamovilapp.data.database.AppDatabase
import com.example.pruebamovilapp.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        viewModelScope.launch {
            if (userDao.getAllUsers().isEmpty()) {
                userDao.insert(
                    User(
                        username = "admin",
                        password = "admin123",
                        fullName = "Antonio Herrera",
                        email = "aherrera@lucky.com.pe",
                        photoUrl = ""
                    )
                )
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByUsername(username)
            if (user?.password == password) {
                _loginSuccess.value = true
                _errorMessage.value = ""
            } else {
                _errorMessage.value = "Credenciales incorrectas"
            }
        }
    }
}