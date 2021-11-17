package com.example.socialnetwork.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDataBaseDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from users WHERE id = :key")
    fun get(key: Int) : User?

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser() : User?

    @Query("SELECT * FROM users")
    fun getAllUsers() : List<User>
}