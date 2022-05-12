package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

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

    fun fieldsAreEmpty(userFields: List<String>): Boolean {
        userFields.forEach{
            if (it.isEmpty())
                return true
        }
        return false
    }

    fun fieldHasSpecialCharacters(userField: String): Boolean{
        val pattern : Pattern = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        val matcher: Matcher = pattern.matcher(userField)
        return matcher.find()
    }
}