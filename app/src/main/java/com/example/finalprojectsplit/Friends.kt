package com.example.finalprojectsplit

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friends(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "friendName") val friendName: String,
    @ColumnInfo(name = "friendEmail") val friendEmail: String,
)

////insert O
//        //make user pick a friend
//do this in repo or activity ?
//        //when they choos