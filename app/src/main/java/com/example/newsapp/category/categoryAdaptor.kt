package com.example.newsapp.category

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.google.android.material.card.MaterialCardView

class categoryAdaptor(val category: List<category_item>) :
    RecyclerView.Adapter<categoryAdaptor.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == categoryLeft)
                R.layout.catogry_left_item else
                R.layout.catogry_right_item, parent, false
        )

        return viewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = category[position]
        holder.catName.setText(item.titleId)
        holder.catImage.setImageResource(item.imageId)
        holder.backGroundCardView.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                item.backGround
            )
        )
        onCategoryClickListener.let { onItemClickListener ->
            holder.itemView.setOnClickListener {
                onItemClickListener?.onCategoryClick(position, item)
            }
        }

    }

    val categoryLeft = 10;
    val categoryRight = 20


    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) categoryLeft else categoryRight
    }

    override fun getItemCount(): Int = category.size
    var onCategoryClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onCategoryClick(position: Int, categorie: category_item)
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val catName: TextView = itemView.findViewById(R.id.category_name)
        val catImage: ImageView = itemView.findViewById(R.id.image_category)
        val backGroundCardView: MaterialCardView = itemView.findViewById(R.id.cardView)
    }
}