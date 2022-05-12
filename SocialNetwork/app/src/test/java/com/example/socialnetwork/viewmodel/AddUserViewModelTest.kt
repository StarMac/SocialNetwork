package com.example.socialnetwork.viewmodel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddUserViewModelTest() {
    private lateinit var addUserViewModel: AddUserViewModel

    @Before
    fun setup() {
        addUserViewModel = AddUserViewModel(ApplicationProvider.getApplicationContext())
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

}