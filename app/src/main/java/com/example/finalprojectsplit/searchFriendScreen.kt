package com.example.finalprojectsplit

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.finalprojectsplit.BedrockURepository.*
import kotlinx.android.synthetic.main.fragment_search_friend_screen.*
var testid:Int =0

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

val situation = arrayOf("You owe the full amount","They owe the full amount")


/**
 * A simple [Fragment] subclass.
 * Use the [searchFriendScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchFriendScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =inflater.inflate(R.layout.fragment_search_friend_screen, container, false)

        val buttonSaveExpense: Button? = view?.findViewById(R.id.saveExpense)
        buttonSaveExpense?.setOnClickListener{view:View -> saveOwe(view)}

        val buttonSit: Button? = view?.findViewById(R.id.changeSituation)
        buttonSit?.setOnClickListener{

            view:View -> buttonSituation(view)

            buttonSaveExpense?.isEnabled=true



        }

        val buttonSearchFriend: Button? = view?.findViewById(R.id.findFriend2)
        buttonSearchFriend?.setOnClickListener{
            view:View -> checkGetId(view)
            buttonSit?.isEnabled=true

        }

        val buttonSkip: Button? = view?.findViewById(R.id.skipToExpense)
        buttonSkip?.setOnClickListener{
            Navigation.findNavController(view).navigate(
                    R.id.action_searchFriendScreen_to_displayFriendScreen)

        }

        /*

        val buttonDeleteFriend: Button? = view?.findViewById(R.id.deleteFriend)
        buttonDeleteFriend?.setOnClickListener{view:View ->deleteTemp(view)}

         */




        buttonSaveExpense?.isEnabled=false
        buttonSit?.isEnabled=false



        repository!!.friendsSearchResultsOwe?.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    //friendName.setText(it[0].friendName)
                    //friendEmail.setText(it[0].friendEmail)


                    val hi=it[0].id
                    testid=hi
                    //displayId.setText("$hi")
                    //displayId.setText("${it[0].id}")



                    //Log.i("mytag","$testid")




                } else {
                    //displayId.setText("error")
                }
            }
        })







        return view
    }

    fun deleteTemp(view: View){
        repository!!.deleteFriend(friendName2.text.toString())
        clearDataEntryFields()
    }


    fun checkGetId(view: View){



        val mlist = repository!!.findFriendOwe(friendName2.text.toString())
        val list = mlist.value




        /*
        if (list != null && !list.isNotEmpty()) {
            friendName2.setText(list.component1().friendName)
            displayId.setText(list.component1().id)

            testid=list.component1().id
            //val localId:Int=list.component1().id
            //Log.i("mytag","$localId")
            //displayId.setText("${it[0].id}")


        } else {
            displayId.setText("ouch")
            //displayId.setText("test")
        }

         */





        //Navigation.findNavController(view).navigate(
        //R.id.action_searchFriendScreen_to_displayFriendScreen)
    }

    fun clearDataEntryFields() {
        friendName2.setText("")

    }


    ///////////////////////////////////////////////////////////////////
    //save expense below://
    fun saveOwe(view: View){

        //repository!!.deleteFriend(friendName.text.toString())

        val owedesc= oweDescription.text.toString()
        val oweamount = oweAmount.text.toString()

        if (owedesc != "" && oweamount != "") {
            val aOwe = Owe(0, testid.toString(),oweamount.toInt(),owedesc, situation.indexOf(oweSituation.text.toString()))
            repository!!.insertOwe(aOwe)
            clearDataEntryFields2()
        } else {
            oweDescription.setText("data entry incomplete")
        }






    }

    fun buttonSituation(view: View){

        var index = situation.indexOf(oweSituation.text.toString())
        index = (index + 1) % 2
        oweSituation.text = situation.get(index)
    }

    private fun clearDataEntryFields2() {
        oweDescription.setText("")
        oweAmount.setText("")


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment searchFriendScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            searchFriendScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}