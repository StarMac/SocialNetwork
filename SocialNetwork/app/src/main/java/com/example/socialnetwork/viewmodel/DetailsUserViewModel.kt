package com.example.socialnetwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserData

class DetailsUserViewModel : ViewModel() {
    private val userData: UserData = UserData()
    private val _userDetailsLiveData = MutableLiveData<User>()
    val userDetailsLiveData = _userDetailsLiveData

    fun loadUserDetailsData(id: Int){
        _userDetailsLiveData.value = userData.userList[id]
    }
}