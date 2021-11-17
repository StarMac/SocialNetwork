package com.example.socialnetwork.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class User (

    @PrimaryKey()
    val id : Int,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_photo")
    val photo: String,

    @ColumnInfo(name = "user_online")
    val onlineStatus: String,

    @ColumnInfo(name = "user_profession")
    val profession: String,

    @ColumnInfo(name = "user_status")
    val status: String)