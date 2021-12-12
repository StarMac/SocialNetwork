package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.launch

class EditUserViewModel(application: Application) : AndroidViewModel(application) {
    private val _userEditLiveData = MutableLiveData<User>()
    val userEditLiveData: LiveData<User> = _userEditLiveData

    fun init(userId: Int) {
        viewModelScope.launch {
            loadUserDetailsData(userId)
        }
    }

    private val userDataBase = UserDataBase.getInstance(application).userDao()

    private suspend fun loadUserDetailsData(id: Int) {
        _userEditLiveData.value = userDataBase.get(id)
    }

    fun onUpdateUser(user: User) {
        viewModelScope.launch {
            userDataBase.update(user)
        }
    }
}