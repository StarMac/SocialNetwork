package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.launch

class DetailsUserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDataBase = UserDataBase.getInstance(application).userDao()
    private val _userDetailsLiveData = MediatorLiveData<User>()
    val userDetailsLiveData: LiveData<User> = _userDetailsLiveData
    fun init(userId: Int) {
        _userDetailsLiveData.addSource(userDao.observeUser(userId)) {
            _userDetailsLiveData.value = it
        }
    }

    private val userDao = UserDataBase.getInstance(application).userDao()

    //    private suspend fun loadUserDetailsData(id: Int) {
//        _userDetailsLiveData.value = userDao.get(id)
//    }
    fun onDeleteUser(id: Int) {
        viewModelScope.launch {
            deleteUser(userDataBase.get(id)!!)
        }
    }

    private suspend fun deleteUser(user: User) {
        userDataBase.delete(user)
    }
}