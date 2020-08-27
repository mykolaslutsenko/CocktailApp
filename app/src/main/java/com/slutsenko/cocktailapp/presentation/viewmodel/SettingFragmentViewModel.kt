package com.slutsenko.cocktailapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.slutsenko.cocktailapp.data.repository.model.UserRepoModel
import com.slutsenko.cocktailapp.data.repository.source.UserRepository
import com.slutsenko.cocktailapp.presentation.mapper.UserModelMapper
import com.slutsenko.cocktailapp.presentation.model.user.UserModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class SettingFragmentViewModel(
        application: Application,
        private val userRepository: UserRepository,
        private val userModelMapper: UserModelMapper
) : BaseViewModel(application) {

    var userLiveData: LiveData<UserModel?> = userRepository.userLiveData.map {
        when {
            it != null -> userModelMapper.mapTo(it)
            else -> null
        }
    }

    var firstNameLiveData: MutableLiveData<String?> = MutableLiveData(userLiveData.value?.name)
    var lastNameLiveData: MutableLiveData<String?> = MutableLiveData(userLiveData.value?.lastName)

//    var localUserLiveData: MutableLiveData<UserModel?> = MediatorLiveData<UserModel?>().apply {
//        fun check() {
//            value = userLiveData.value
//        }
//
//        addSource(userLiveData) {
//            check()
//        }
//    }


    fun updateUser() {
        launchRequest {
            //userRepository.updateUser(userModelMapper.mapFrom(localUserLiveData.value!!))
            userRepository.updateUser(
                    UserRepoModel(userLiveData.value!!.id, firstNameLiveData.value!!, lastNameLiveData.value!!, userLiveData.value!!.email))
        }
    }
}