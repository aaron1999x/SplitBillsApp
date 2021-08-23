package com.example.finalprojectsplit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectsplit.*
import kotlinx.android.synthetic.main.fragment_add_friend_screen.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {



    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view= inflater.inflate(R.layout.main_fragment, container, false)

        //////////////////////Display Friend List///////////////////////////
        repository!!.allFriends?.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter?.setFriendList(it)
            }
        })

        repository!!.friendsSearchResults?.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    friendName.setText(it[0].friendName)
                    friendEmail.setText(it[0].friendEmail)


                    //val testid=it[0].id


                } else {
                    friendName.setText("match not found")
                }
            }
        })
        adapter = FriendListingAdapter(R.layout.card2)
        val recyclerView: RecyclerView? = view.findViewById(R.id.friendListing)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter
        //////////////////////////






        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel



        toAddFriend.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_mainFragment_to_addFriendScreen)
        }

        toFriend.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_mainFragment_to_searchFriendScreen)
        }








    }



}