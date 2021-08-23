package com.example.finalprojectsplit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_display_friend_screen.*
import kotlinx.android.synthetic.main.fragment_search_friend_screen.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.fragment_add_friend_expense.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var test:Int = 0
var tt:TextView?=null

/**
 * A simple [Fragment] subclass.
 * Use the [displayFriendScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class displayFriendScreen : Fragment() {
    // TODO: Rename and change types of parameters

    private var specialName: TextView?=null  //initialize textview


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
        val view=  inflater.inflate(R.layout.fragment_display_friend_screen, container, false)

        specialName = view.findViewById(R.id.specialName)//initialize textview
        tt=view?.findViewById(R.id.testTotal)







        displayNameText(view)

        val buttonExpense: Button? = view?.findViewById(R.id.toAddExpense)
        buttonExpense?.setOnClickListener{view:View -> goToAddExp(view)}

        val buttonDeleteDes: Button? = view?.findViewById(R.id.deleteD)
        buttonDeleteDes?.setOnClickListener{view:View -> deleteTemp1(view)}





        ////////////////////////////////////////////////////////////////////////////////
        //Display owe list

        repository!!.allOwes?.observe(viewLifecycleOwner, Observer {
            it?.let {
                var justone = it.toMutableList()


                for (a in it)
                    if (a.id != testid.toString())
                        justone.remove(a)

                adapter2?.setExpenseList(justone)


            }
        })




        repository!!.oweSearchResultName?.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    oweDescription.setText(it[0].description)
                    oweAmount.setText(it[0].oweAmount)
                    oweSituation.setText("${situation[it[0].statusCode.toInt()]}")





                } else {
                    oweDescription.setText("error")
                }
            }
        })
        adapter2 = FriendExpenseListingAdapter(R.layout.card)
        val recyclerView: RecyclerView? = view.findViewById(R.id.expenseListing)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter2






        return view
    }

    fun displayNameText(view: View){


        val mlist = repository!!.displayFriendName(testid.toString())
        val list = mlist.value





        if (list != null && !list.isEmpty()) {
            specialName?.setText(list.component1().friendName)



        } else {
            specialName?.setText("you did not select a friend ")
            //displayId.setText("test")
        }

        repository!!.friendsSearchResultsOwe?.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    //friendName.setText(it[0].friendName)
                    //friendEmail.setText(it[0].friendEmail)

                    specialName?.setText("${it[0].friendName}'s balance:")

                    //displayId.setText("${it[0].id}")


                    //testid=it[0].id


                } else {
                    //displayId.setText("error")
                }
            }
        })



    }

    fun goToAddExp(view: View){
        Navigation.findNavController(view).navigate(
            R.id.action_displayFriendScreen_to_searchFriendScreen)
    }

    fun deleteTemp1(view: View){
        repository!!.deleteExpense(enterDescription.text.toString())

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment displayFriendScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            displayFriendScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}