package com.slutsenko.cocktailapp.presentation.mapper

import com.slutsenko.cocktailapp.data.repository.model.UserRepoModel
import com.slutsenko.cocktailapp.presentation.mapper.base.BaseModelMapper
import com.slutsenko.cocktailapp.presentation.model.user.UserModel

class UserModelMapper(
) : BaseModelMapper<UserModel, UserRepoModel>() {

    override fun mapFrom(model: UserModel) = with(model) {
        UserRepoModel(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar
        )
    }

    override fun mapTo(model: UserRepoModel)= with(model) {
        UserModel(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar
        )
    }
}
