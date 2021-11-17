package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserData
import com.example.socialnetwork.model.UserDataBase

class DetailsUserViewModel (application: Application) : AndroidViewModel(application) {
    private val _userDetailsLiveData = MutableLiveData<User>()
    val userDetailsLiveData : LiveData<User> = _userDetailsLiveData

    private val userDataBase = UserDataBase.getInstance(application).userDatabaseDao()

    fun loadUserDetailsData(id: Int){
        _userDetailsLiveData.value = userDataBase.get(id)
    }

    fun updateUser(user : User){
        userDataBase.update(user)
    }
}