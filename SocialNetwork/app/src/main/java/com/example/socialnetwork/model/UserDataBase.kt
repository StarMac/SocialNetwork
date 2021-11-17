package com.example.socialnetwork.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase :RoomDatabase(){

    abstract fun userDatabaseDao(): UserDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE : UserDataBase? = null

        fun getInstance(context : Context) : UserDataBase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "users_database")
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}