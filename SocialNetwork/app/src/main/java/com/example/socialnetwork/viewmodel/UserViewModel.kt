package com.example.socialnetwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserData

class UserViewModel :ViewModel() {
    private val userData: UserData = UserData()
    private val _userLiveData = MutableLiveData<List<User>>()
    val userLiveData : LiveData<List<User>> = _userLiveData

    private val _userDetailsLiveData = MutableLiveData<User>()
    val userDetailsLiveData = _userDetailsLiveData

    fun loadUserData(){
        _userLiveData.value = userData.userList
    }

    fun loadUserDetailsData(id: Int){
        _userDetailsLiveData.value = userData.userList[id]
    }
}