package com.slutsenko.cocktailapp.data.repository.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository

interface TokenRepository : BaseRepository {

    val tokenLiveData: LiveData<String?>
    var token: String?

}