package com.slutsenko.cocktailapp.data.lokal.source

import androidx.lifecycle.LiveData

interface TokenLocalSource {
    val tokenLiveData: LiveData<String?>
    var token: String?
}