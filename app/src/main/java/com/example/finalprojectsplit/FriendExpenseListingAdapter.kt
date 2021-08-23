package com.example.finalprojectsplit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_display_friend_screen.*
import java.lang.NullPointerException

var total: Int=0


class FriendExpenseListingAdapter(private val expenseItemLayout: Int):
    RecyclerView.Adapter<FriendExpenseListingAdapter.ViewHolder>() {

    private var expenseList: List<Owe>? = null
    private var cardImages= R.drawable.rec

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var item: TextView = itemView.findViewById(R.id.expense_row)

        var image: ImageView= itemView.findViewById((R.id.cardImage3))

        var itemdesc: TextView = itemView.findViewById(R.id.friend1_row)
        var itemstatus: TextView = itemView.findViewById(R.id.status_row)
        var itemamount: TextView = itemView.findViewById(R.id.amount_row)






    }

    fun setExpenseList(expenses: List<Owe>) {
        expenseList = expenses
        total=0


        notifyDataSetChanged()

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int



    ): FriendExpenseListingAdapter.ViewHolder {

         val view = LayoutInflater.from(parent.context).inflate(
            expenseItemLayout, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (expenseList == null) 0 else expenseList!!.size
    }

    override fun onBindViewHolder(holder: FriendExpenseListingAdapter.ViewHolder, position: Int) {
        //val item = holder.item
        var itemdesc=holder.itemdesc
        var itemstatus=holder.itemstatus
        var itemamount=holder.itemamount




        holder.image.setImageResource(cardImages)
        expenseList.let {
            itemdesc.text= "${it!![position].description}"
            itemstatus.text="${situation[it!![position].statusCode.toInt()]}"
            itemamount.text="$ ${it!![position].oweAmount}"
            //item.text = "${it!![position].id},${it!![position].description}, ${it!![position].oweAmount},${situation[it!![position].statusCode.toInt()]}"
            val dummy= it!![position].oweAmount
            total += dummy

        }

        Log.i("total","$total")
        tt?.text= "$"+total.toString()







    }
}