package com.example.funvacation.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funvacation.R
import com.example.funvacation.data.DestinationData
import com.example.funvacation.ui.detail.DetailActivity

class ListDestinationAdapter (private val listDestination : ArrayList<DestinationData>) : RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvPlace : TextView = itemView.findViewById(R.id.tv_item_place)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDestination.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, place, description, photo, link) = listDestination[position]
        val mContext  = holder.itemView.context
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvPlace.text = place
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            moveDetail.putExtra(DetailActivity.EXTRA_PHOTO, photo)
            moveDetail.putExtra(DetailActivity.EXTRA_LOCATION, place)
            moveDetail.putExtra(DetailActivity.EXTRA_DESC, description)
            moveDetail.putExtra(DetailActivity.EXTRA_LINK, link)
            mContext.startActivity(moveDetail)
        }
    }

}