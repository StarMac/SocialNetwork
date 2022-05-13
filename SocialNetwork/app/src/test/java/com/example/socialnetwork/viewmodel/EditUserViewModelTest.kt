package com.example.socialnetwork.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDao
import com.example.socialnetwork.model.UserDataBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditUserViewModelTest {
    private lateinit var editUserViewModel: EditUserViewModel
    private lateinit var userDataBase: UserDataBase
    private lateinit var userDao: UserDao
    private val userLiveData = MutableLiveData<User>()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        editUserViewModel = EditUserViewModel(ApplicationProvider.getApplicationContext())
        userDataBase = Room.inMemoryDatabaseBuilder(context, UserDataBase::class.java).build()
        userDao = userDataBase.userDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadUserDetailsData_userJhonny_returnsJhonny() = runTest {
        val user = User(1, "Jhonny", "test", "1 seconds ago", "tester", "I love tests")
        userDao.insert(user)
        userLiveData.value = userDao.get(1)
        val result = userLiveData.value
        assertThat(result!!.name, `is`("Jhonny"))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateUser_userJhonny_returnsRonald() = runTest {
        var user = User(1, "Jhonny", "test", "1 seconds ago", "tester", "I love tests")
        userDao.insert(user)
        user = User(1, "Ronald", "test", "1 seconds ago", "tester", "I love tests")
        userDao.update(user)
        val result = userDao.get(1)
        assertThat(result!!.name, `is`("Ronald"))
    }

    @Test
    fun fieldsAreEmpty_listIsEmpty_returnsTrue() {
        val fields = listOf("")
        val result = editUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(true))
    }

    @Test
    fun fieldsAreEmpty_fieldsContainEmpty_returnsTrue() {
        val fields = listOf("name", "", "test")
        val result = editUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(true))
    }

    @Test
    fun fieldsAreEmpty_AreNotEmpty_returnsFalse() {
        val fields = listOf("test1", "test2", "test3")
        val result = editUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldNoSpecialChar_returnsFalse() {
        val field = "Peter"
        val result = editUserViewModel.fieldHasSpecialCharacters(field)
        assertThat(result, `is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldHasSpecialChar_returnsTrue() {
        val field = "Jhon_S1mpl.e"
        val result = editUserViewModel.fieldHasSpecialCharacters(field)
        assertThat(result, `is`(true))
    }

    @After
    fun closeDB() {
        userDataBase.close()
    }
}