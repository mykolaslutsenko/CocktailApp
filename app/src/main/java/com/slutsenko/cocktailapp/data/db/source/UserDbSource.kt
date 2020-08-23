package com.slutsenko.cocktailapp.data.db.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.db.model.UserDbModel

interface UserDbSource: BaseDbSource {

    val userLiveData: LiveData<UserDbModel?>

    suspend fun getUser(): UserDbModel?
    suspend fun addOrReplaceUser(user: UserDbModel)
    suspend fun saveUser(user: UserDbModel)
    suspend fun deleteUser()
}