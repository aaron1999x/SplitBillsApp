package com.example.finalprojectsplit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OweDao {

    @Insert
    fun insertOwe(owe: Owe)


    @Query("SELECT * FROM owes")
    fun getAllOwe(): LiveData<List<Owe>>

    @Query("SELECT * FROM owes WHERE id = :num")
    fun displayOweList(num: String): List<Owe>

    @Query("DELETE FROM owes WHERE description = :des")
    fun deleteExpense(des: String)


}