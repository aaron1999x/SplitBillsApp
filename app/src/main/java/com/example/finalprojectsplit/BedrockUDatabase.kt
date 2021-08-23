package com.example.finalprojectsplit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Friends::class),(Owe::class)], version = 1)
abstract class BedrockUDatabase: RoomDatabase() {

    abstract fun friendDao(): FriendDao
    abstract fun oweDao(): OweDao


    companion object {

        private var INSTANCE: BedrockUDatabase? = null

        internal fun getDatabase(context: Context): BedrockUDatabase? {

            if (INSTANCE == null) {
                synchronized(BedrockUDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder<BedrockUDatabase>(
                                context.applicationContext,
                                BedrockUDatabase::class.java,
                                "bedrocku_database"
                            ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}