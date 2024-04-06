package com.example.faculty

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val gender: String,
    val department: String
)

// Member.kt
data class Member(
    val name: String,
    val email: String,
    val phone: String
)
