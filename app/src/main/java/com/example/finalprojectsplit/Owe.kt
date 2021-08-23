package com.example.finalprojectsplit

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owes")
data class Owe(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "oweid") var oweid: Int = 0,

    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "oweAmount") val oweAmount: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "statusCode") val statusCode: Int,


    )



