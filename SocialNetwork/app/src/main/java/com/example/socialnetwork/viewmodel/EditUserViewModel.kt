package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase

class EditUserViewModel (application: Application) : AndroidViewModel(application) {
    private val _userEditLiveData = MutableLiveData<User>()
    val userEditLiveData : LiveData<User> = _userEditLiveData

    private val userDataBase = UserDataBase.getInstance(application).userDatabaseDao()

    fun loadUserDetailsData(id: Int){
        _userEditLiveData.value = userDataBase.get(id)
    }

    fun updateUser(user : User){
        userDataBase.update(user)
    }
}