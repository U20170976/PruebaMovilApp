package com.example.pruebamovilapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
    val fullName: String,
    val email: String,
    val photoUrl: String
)
