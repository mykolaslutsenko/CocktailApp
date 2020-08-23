package com.slutsenko.cocktailapp.data.repository.impl.source

import com.slutsenko.cocktailapp.data.db.source.UserDbSource
import com.slutsenko.cocktailapp.data.lokal.source.TokenLocalSource
import com.slutsenko.cocktailapp.data.network.source.AuthNetSource
import com.slutsenko.cocktailapp.data.network.source.UserNetSource
import com.slutsenko.cocktailapp.data.repository.impl.mapper.UserRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.source.AuthRepository

class AuthRepositoryImpl(
        private val authNetSource: AuthNetSource,
        private val userNetSource: UserNetSource,
        private val userDbSource: UserDbSource,
        private val userModelMapper: UserRepoModelMapper,
        private val tokenLocalSource: TokenLocalSource
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): Boolean {
        return authNetSource.signIn(email, password)
            .let {
                tokenLocalSource.token = it

                //refresh user
                userNetSource.getUser()
                    .run(userModelMapper::mapNetToDb)
                    .run { userDbSource.saveUser(this) }

                tokenLocalSource.token != null
            }
    }

    override suspend fun signUp(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean {
        return authNetSource
            .signUp(name, lastName, email, password)
            .let {
                tokenLocalSource.token = it

                //refresh user
                userNetSource.getUser()
                    .run(userModelMapper::mapNetToDb)
                    .run { userDbSource.saveUser(this) }

                tokenLocalSource.token != null
            }
    }
}
