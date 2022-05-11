package com.example.socialnetwork.viewmodel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditUserViewModelTest {
    private lateinit var editUserViewModel: EditUserViewModel

    @Before
    fun setup(){
        editUserViewModel = EditUserViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun fieldsIsEmpty_listIsEmpty_returnsTrue() {
        val fields = listOf("")
        val result = editUserViewModel.fieldsIsEmpty(fields)
        MatcherAssert.assertThat(result, Is.`is`(true))
    }

    @Test
    fun fieldsIsEmpty_fieldsContainsEmpty_returnsTrue() {
        val fields = listOf("name", "", "test")
        val result = editUserViewModel.fieldsIsEmpty(fields)
        MatcherAssert.assertThat(result, Is.`is`(true))
    }

    @Test
    fun fieldsIsEmpty_IsNotEmpty_returnsFalse() {
        val fields = listOf("test1", "test2", "test3")
        val result = editUserViewModel.fieldsIsEmpty(fields)
        MatcherAssert.assertThat(result, Is.`is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldNoSpecialChar_returnsFalse() {
        val field = "Peter"
        val result = editUserViewModel.fieldHasSpecialCharacters(field)
        MatcherAssert.assertThat(result, Is.`is`(false))
    }

    @Test
    fun fieldHasSpecialCharacters_fieldHasSpecialChar_returnsTrue() {
        val field = "Jhon_S1mpl.e"
        val result = editUserViewModel.fieldHasSpecialCharacters(field)
        MatcherAssert.assertThat(result, Is.`is`(true))
    }
}