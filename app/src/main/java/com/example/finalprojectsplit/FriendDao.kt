package com.example.finalprojectsplit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FriendDao {

    @Insert
    fun insertFriend(friend: Friends)

    @Query("SELECT * FROM friends")
    fun getAllFriends(): LiveData<List<Friends>>


    @Query("SELECT * FROM friends WHERE friendName = :fullname")
    fun findFriend(fullname: String): List<Friends>

    @Query("SELECT * FROM friends WHERE id = :num")
    fun displayFriendName(num: String): List<Friends>



    @Query("SELECT * FROM friends WHERE friendName = :fullname")
    fun findFriendOwe(fullname: String): List<Friends>

    @Query("DELETE FROM friends WHERE friendName = :fullname")
    fun deleteFriend(fullname: String)
}