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
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddUserViewModelTest {
    private lateinit var addUserViewModel: AddUserViewModel
    private lateinit var userDataBase: UserDataBase
    private lateinit var userDao: UserDao
    private val userLiveData = MutableLiveData<User>()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        addUserViewModel = AddUserViewModel(ApplicationProvider.getApplicationContext())
        userDataBase = Room.inMemoryDatabaseBuilder(context, UserDataBase::class.java).build()
        userDao = userDataBase.userDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertUser_userJhonny_returnsJhonny() = runTest {
        val user = User(1, "Jhonny", "test", "1 seconds ago", "tester", "I love tests")
        userDao.insert(user)
        userLiveData.value = userDao.get(1)
        val result = userLiveData.value
        assertThat(result!!.name, `is`("Jhonny"))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun init_userCount_returnsOne() = runTest {
        val user = User(1, "Jhonny", "test", "1 seconds ago", "tester", "I love tests")
        userDao.insert(user)
        val result = userDao.count()
        assertThat(result, `is`(1))
    }

    @Test
    fun fieldsAreEmpty_listIsEmpty_returnsTrue() {
        val fields = listOf("")
        val result = addUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(true))
    }


    @Test
    fun fieldsAreEmpty_fieldsContainEmpty_returnsTrue() {
        val fields = listOf("Test", "test", "")
        val result = addUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(true))
    }

    @Test
    fun fieldsAreEmpty_AreNotEmpty_returnsFalse() {
        val fields = listOf("Test", "test", "test2?")
        val result = addUserViewModel.fieldsAreEmpty(fields)
        assertThat(result, `is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldNoSpecialChar_returnsFalse() {
        val field = "Harry"
        val result = addUserViewModel.fieldHasSpecialCharacters(field)
        assertThat(result, `is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldHasSpecialChar_returnsTrue() {
        val field = "1t's_The_C0llest_N@me_Of_Th3_World"
        val result = addUserViewModel.fieldHasSpecialCharacters(field)
        assertThat(result, `is`(true))
    }

    @After
    fun closeDB() {
        userDataBase.close()
    }

}