package com.slutsenko.cocktailapp.data.repository.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.repository.model.UserRepoModel
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository
import java.io.File

interface UserRepository : BaseRepository {


    val userLiveData: LiveData<UserRepoModel?>
    /**
     * @return true - if user has already its profile data filled (go to Main)
     * Otherwise user must fill profile data
     */
    suspend fun getUser(): UserRepoModel?

    suspend fun refreshUser()

    suspend fun updateUser(user: UserRepoModel)

    suspend fun updateUserAvatar(avatar: File, onUploadProgress: (Float) -> Unit = { _ -> }): String

    suspend fun deleteUser()
}