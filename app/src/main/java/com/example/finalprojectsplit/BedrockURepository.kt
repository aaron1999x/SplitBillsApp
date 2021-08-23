package com.example.finalprojectsplit

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//check splittestdB
//import com.example.splittestdb.MainActivity.*

class BedrockURepository(application: Application) {

    val friendsSearchResults = MutableLiveData<List<Friends>>()
    val friendsSearchResultsOwe = MutableLiveData<List<Friends>>()
    val friendsSearchResultName= MutableLiveData<List<Friends>>()

    val oweSearchResultName= MutableLiveData<List<Owe>>()



    //need an async
    //need an quesry async

    fun asyncFinishedFriends(results: List<Friends>): MutableLiveData<List<Friends>> {
        friendsSearchResults.value = results

        return friendsSearchResults

    }



    private class QueryAsyncTaskStudents constructor(val asyncTaskDao: FriendDao?) :
        AsyncTask<String, Void, List<Friends>>() {

        var delegate: BedrockURepository? = null

        override fun doInBackground(vararg params: String): List<Friends>? {
            return asyncTaskDao?.findFriend(params[0])
        }

        override fun onPostExecute(result: List<Friends>) {
            delegate?.asyncFinishedFriends(result)
        }
    }


    private class InsertAsyncTaskFriends constructor(
        private val asyncTaskDao: FriendDao?
    ) : AsyncTask<Friends, Void, Void>() {

        override fun doInBackground(vararg params: Friends): Void? {
            asyncTaskDao?.insertFriend(params[0])
            return null
        }
    }



    private class InsertAsyncTaskOwe constructor(
        private val asyncTaskDao: OweDao?
    ) : AsyncTask<Owe, Void, Void>() {

        override fun doInBackground(vararg params: Owe): Void? {
            asyncTaskDao?.insertOwe(params[0])
            return null
        }
    }



    private class DeleteAsyncTaskStudents constructor(
        private val asyncTaskDao: FriendDao?
    ) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg params: String): Void? {
            asyncTaskDao?.deleteFriend(params[0])
            return null
        }
    }



    //////////////////////////////////////

    private var friendDao: FriendDao?
    val allFriends: LiveData<List<Friends>>?


    private var oweDao: OweDao?
    val allOwes: LiveData<List<Owe>>?




    init {
        val db: BedrockUDatabase? = BedrockUDatabase.getDatabase(application)

        friendDao = db?.friendDao()
        allFriends = friendDao?.getAllFriends()


        oweDao=db?.oweDao()
        allOwes=oweDao?.getAllOwe()



    }

    fun insertFriend(newfriend: Friends) {
        val task = InsertAsyncTaskFriends(friendDao)
        task.execute(newfriend)
    }

    fun deleteFriend(name: String) {
        val task = DeleteAsyncTaskStudents(friendDao)
        task.execute(name)
    }



    fun findFriend(name: String): MutableLiveData<List<Friends>> {
        val task = QueryAsyncTaskStudents(friendDao)
        task.delegate = this
        task.execute(name)
        return friendsSearchResults
    }


    //////////////////////////////////////


    fun insertOwe(newowe: Owe) {
        val task = InsertAsyncTaskOwe(oweDao)
        task.execute(newowe)
    }








    /////////////////////////////////////////

    private class QueryAsyncTaskFriendsOwe constructor(val asyncTaskDao: FriendDao?) :
        AsyncTask<String, Void, List<Friends>>() {

        var delegate: BedrockURepository? = null

        override fun doInBackground(vararg params: String): List<Friends>? {
            return asyncTaskDao?.findFriendOwe(params[0])
        }

        override fun onPostExecute(result: List<Friends>) {
            delegate?.asyncFinishedFriendsOwe(result)
        }
    }



    fun asyncFinishedFriendsOwe(results: List<Friends>): MutableLiveData<List<Friends>> {
        friendsSearchResultsOwe.value = results
        return friendsSearchResultsOwe
    }




    fun findFriendOwe(name: String): MutableLiveData<List<Friends>> {
        val task = QueryAsyncTaskFriendsOwe(friendDao)
        task.delegate = this
        task.execute(name)
        return friendsSearchResults
    }

    /////////////////////////////////////////////////////////////////////////
    //Display all owed list

    private class QueryAsyncTaskOwe constructor(val asyncTaskDao: OweDao?) :
        AsyncTask<String, Void, List<Owe>>() {

        var delegate: BedrockURepository? = null

        override fun doInBackground(vararg params: String): List<Owe>? {
            return asyncTaskDao?.displayOweList(params[0])
        }

        override fun onPostExecute(result: List<Owe>) {
            delegate?.asyncFinishedOwe(result)
        }
    }



    fun asyncFinishedOwe(results: List<Owe>): MutableLiveData<List<Owe>> {
        oweSearchResultName.value = results
        return oweSearchResultName
    }




    fun displayOweList(numb: String): MutableLiveData<List<Owe>> {
        val task = QueryAsyncTaskOwe(oweDao)
        task.delegate = this
        task.execute(numb)
        return oweSearchResultName
    }

    private class DeleteAsyncTaskDescription constructor(
            private val asyncTaskDao: OweDao?
    ) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg params: String): Void? {
            asyncTaskDao?.deleteExpense(params[0])
            return null
        }
    }

    fun deleteExpense(name: String) {
        val task = DeleteAsyncTaskDescription(oweDao)
        task.execute(name)
    }



    ///////////////////////////////////////////////////////////////
    //Display Name of ID chosen




    private class QueryAsyncTaskFriendsName constructor(val asyncTaskDao: FriendDao?) :
        AsyncTask<String, Void, List<Friends>>() {

        var delegate: BedrockURepository? = null



        override fun doInBackground(vararg params: String): List<Friends>? {
            return asyncTaskDao?.displayFriendName(params[0])
        }



        override fun onPostExecute(result: List<Friends>) {
            delegate?.asyncFinishedFriendsName(result)
        }
    }

    fun asyncFinishedFriendsName(results: List<Friends>): MutableLiveData<List<Friends>> {
        friendsSearchResultName.value = results
        return friendsSearchResultName
    }

    fun displayFriendName(numb: String): MutableLiveData<List<Friends>> {
        val task = QueryAsyncTaskFriendsName(friendDao)
        task.delegate = this
        task.execute(numb)
        return friendsSearchResultName
    }

    ///////////////////////////////////////////////////////////////////








}