package com.slutsenko.cocktailapp.data.repository.model

data class UserRepoModel(
        val id: Long = 1L,
        val name: String = "",
        val lastName: String = "",
        val email: String = "",
        val avatar: String? = null
)