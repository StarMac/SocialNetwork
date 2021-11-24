package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserData
import com.example.socialnetwork.model.UserDataBase

class UserViewModel (application: Application) : AndroidViewModel(application) {
    private val userData: UserData = UserData()
    private val _userLiveData = MutableLiveData<List<User>>()
    val userLiveData : LiveData<List<User>> = _userLiveData

    private val userDataBase = UserDataBase.getInstance(application).userDatabaseDao()

    fun loadUserData(){
        _userLiveData.value = userDataBase.getAllUsers()
    }

    fun insertUserToDataBase(){
        if(userDataBase.getUser() == null) {
            for (user in userData.userList)
                userDataBase.insert(user)
        }
    }
}