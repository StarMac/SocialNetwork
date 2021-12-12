package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserData
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userData: UserData = UserData()
    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> = _usersLiveData
    private val usersDataBase = UserDataBase.getInstance(application).userDao()
    val users = usersDataBase.getAllUsersLiveData()

    init {
        viewModelScope.launch {
            insertUsersToDataBase()
        }
        viewModelScope.launch(Dispatchers.Main) {
            loadUsersData()
        }
    }

    private suspend fun loadUsersData() {
        _usersLiveData.value = usersDataBase.getAllUsers()
    }

    private suspend fun insertUsersToDataBase() {
        if (usersDataBase.count() == 0) {
            for (user in userData.userList)
                usersDataBase.insert(user)
        }
    }

}