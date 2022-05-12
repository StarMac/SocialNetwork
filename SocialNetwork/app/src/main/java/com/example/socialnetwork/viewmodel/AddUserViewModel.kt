package com.example.socialnetwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class AddUserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDataBase = UserDataBase.getInstance(application).userDao()
    var count = 0

    init {
        viewModelScope.launch {
            count = userDataBase.count()
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userDataBase.insert(user)
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