package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.launch

class AddUserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDataBase = UserDataBase.getInstance(application).userDao()
    var count = 0
    init {
        viewModelScope.launch {
            count = userDataBase.count()
        }
    }

    fun onInsertUser(user: User) {
        viewModelScope.launch {
            insertUser(user)
        }
    }

    private suspend fun insertUser(user: User) {
        userDataBase.insert(user)
    }
}