package com.example.finalprojectsplit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_add_friend_screen.*
import com.example.finalprojectsplit.MainActivity.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addFriendScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class addFriendScreen : Fragment() {
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
        val view=  inflater?.inflate(R.layout.fragment_add_friend_screen, container, false)

        val buttonAddFriend: Button? = view?.findViewById(R.id.saveFriend)
        buttonAddFriend?.setOnClickListener{view:View -> saveFriendTest(view)}



        return view
    }

    fun saveFriendTest(view: View){

        repository!!.deleteFriend(friendName.text.toString())

        val name= friendName.text.toString()
        val email = friendEmail.text.toString()

        if (name != "" && email != "") {
            val aFriend = Friends(0, name,email)
            repository!!.insertFriend(aFriend)
            clearDataEntryFields()
        } else {
            friendName.setText("data entry incomplete")
        }




    }

    private fun clearDataEntryFields() {
        friendName.setText("")
        friendEmail.setText("")

    }






    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addFriendScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addFriendScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}