package com.example.newsapp.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityCatogryRightItemBinding
import com.example.newsapp.databinding.CatogryLeftItemBinding

class categoryAdaptor(val category: List<Category_item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val categoryLeft = 10
    val categoryRight = 20
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) categoryLeft else categoryRight
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == categoryLeft) {
            val view: CatogryLeftItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.catogry_left_item, parent, false
            )
            return LeftViewHolder(view)

        }
        val view: ActivityCatogryRightItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.activity_catogry_right_item, parent, false
        )
        return rightViewHolder(view)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = category[position]
        if (holder is rightViewHolder) {
            holder.bindView(item, holder)
        } else if (holder is LeftViewHolder) {
            holder.bindView(item, holder)
        }
        onCategoryClickListener.let { onItemClickListener ->
            holder.itemView.setOnClickListener {
                onItemClickListener?.onCategoryClick(position, item)
            }
        }

    }


    override fun getItemCount(): Int = category.size
    var onCategoryClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onCategoryClick(position: Int, categorie: Category_item)
    }

    inner class rightViewHolder(val catogryRightItemBinding: ActivityCatogryRightItemBinding) :
        RecyclerView.ViewHolder(catogryRightItemBinding.root) {
        fun bindView(item: Category_item?, holder: RecyclerView.ViewHolder) {
            catogryRightItemBinding.categoryName.setText(item?.titleId!!)
            catogryRightItemBinding.imageCategory.setImageResource(item.imageId)
            catogryRightItemBinding.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context, item.backGround
                )
            )

        }
    }


    inner class LeftViewHolder(val catogryLeftItemBinding: CatogryLeftItemBinding) :
        RecyclerView.ViewHolder(catogryLeftItemBinding.root) {
        fun bindView(item: Category_item?, holder: RecyclerView.ViewHolder) {
            catogryLeftItemBinding.categoryName.setText(item?.titleId!!)
            catogryLeftItemBinding.imageCategory.setImageResource(item.imageId)
            catogryLeftItemBinding.cardView.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, item.backGround)
            )

        }
    }


}


