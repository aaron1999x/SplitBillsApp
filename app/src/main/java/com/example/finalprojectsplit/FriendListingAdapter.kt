package com.example.finalprojectsplit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendListingAdapter(private val friendItemLayout: Int):
    RecyclerView.Adapter<FriendListingAdapter.ViewHolder>() {

    private var friendList: List<Friends>? = null
    private var cardImages = R.drawable.blank



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.friend1_row)
        var image: ImageView = itemView.findViewById((R.id.cardImage3))
    }

    fun setFriendList(students: List<Friends>) {
        friendList = students

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendListingAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            friendItemLayout, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (friendList == null) 0 else friendList!!.size
    }

    override fun onBindViewHolder(holder: FriendListingAdapter.ViewHolder, position: Int) {
        val item = holder.item
        holder.image.setImageResource(cardImages)
        friendList.let {
            item.text = "${it!![position].friendName}"
        }
    }
}
