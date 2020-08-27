package com.slutsenko.cocktailapp.data.repository.impl.mapper

import com.slutsenko.cocktailapp.data.db.model.UserDbModel
import com.slutsenko.cocktailapp.data.network.model.user.UserNetModel
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.model.UserRepoModel


class UserRepoModelMapper : BaseRepoModelMapper<UserRepoModel, UserDbModel, UserNetModel>() {
    override fun mapDbToRepo(db: UserDbModel): UserRepoModel = with(db) {
        UserRepoModel(
                id = id,
                name = name,
                lastName = lastName,
                email = email,
                avatar = avatar
        )
    }

    override fun mapRepoToDb(repo: UserRepoModel) = with(repo) {
        UserDbModel(
                id = id,
                name = name,
                lastName = lastName,
                email = email,
                avatar = avatar
        )
    }

    override fun mapNetToRepo(net: UserNetModel) = with(net) {
        UserRepoModel(
                id = id,
                name = name,
                lastName = lastName,
                email = email,
                avatar = avatar
        )
    }

    override fun mapNetToDb(net: UserNetModel) = with(net) {
        UserDbModel(
                id = id,
                name = name,
                lastName = lastName,
                email = email,
                avatar = avatar
        )
    }

    override fun mapRepoToNet(repo: UserRepoModel)=with(repo) {
        UserNetModel(
                id = id,
                name = name,
                lastName = lastName,
                email = email,
                avatar = avatar
        )
    }
}