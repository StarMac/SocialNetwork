package com.example.socialnetwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.model.UserData

class UserViewModel :ViewModel() {
    private val userData: UserData = UserData()
    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData : LiveData<UserData> = _userLiveData

    fun loadUserData(){
        _userLiveData.value = userData
    }
}