package com.example.socialnetwork.viewmodel

import android.content.Context
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
class DetailsUserViewModelTest {
    private lateinit var userDataBase: UserDataBase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userDataBase = Room.inMemoryDatabaseBuilder(context, UserDataBase::class.java).build()
        userDao = userDataBase.userDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteUserBase_userJhonny_returnsZero() = runTest {
        val user = User(1, "Jhonny", "test", "1 seconds ago", "tester", "I love tests")
        userDao.insert(user)
        userDao.delete(user)
        val result = userDao.count()
        assertThat(result, `is`(0))
    }

    @After
    fun closeDB() {
        userDataBase.close()
    }
}