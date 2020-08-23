package com.slutsenko.cocktailapp.data.db.impl.source

import com.slutsenko.cocktailapp.data.db.impl.dao.UserDao
import com.slutsenko.cocktailapp.data.db.model.UserDbModel
import com.slutsenko.cocktailapp.data.db.source.UserDbSource

class UserDbSourceImpl(
    private val userDao: UserDao
) : UserDbSource {

    override val userLiveData = userDao.userLiveData

    override suspend fun getUser(): UserDbModel? {
        return userDao.getUser()
    }

    override suspend fun addOrReplaceUser(user: UserDbModel) {
        userDao.addOrReplaceUser(user)
    }

    override suspend fun saveUser(user: UserDbModel) {
        userDao.saveUser(user)
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}