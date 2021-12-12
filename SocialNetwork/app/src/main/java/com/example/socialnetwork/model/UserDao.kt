package com.example.socialnetwork.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun  update(user: User)

    @Query("SELECT * from users WHERE id = :key")
    suspend fun get(key: Int) : User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun count() : Int

    @Query("SELECT * from users WHERE id = :key")
    fun observeUser(key: Int) : LiveData<User?>

    @Query("SELECT * FROM users")
    suspend fun getAllUsers() : List<User>

    @Query("SELECT * FROM users")
    fun getAllUsersLiveData() : LiveData<List<User>>

}