package com.example.newsapp.News

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.apiResponses.ArticlesItem
import com.example.newsapp.databinding.NewsItemBinding

class newsAdaptor(var newsItem: List<ArticlesItem?>?) :
    RecyclerView.Adapter<newsAdaptor.viewModel>() {
    fun changeData(newsItem: List<ArticlesItem?>?) {
        this.newsItem = newsItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewModel {
        val view: NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.news_item, parent, false
        )
        return viewModel(view)
    }

    override fun onBindViewHolder(holder: viewModel, position: Int) {
        val item = newsItem?.get(position)
        holder.bindView(item)

        onItemSelectedListener.let { onItemSelectedListener ->
            holder.itemView.setOnClickListener {
                onItemSelectedListener?.onNewsClick(item)
            }
        }
    }


    override fun getItemCount(): Int = newsItem?.size ?: 0

    var onItemSelectedListener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onNewsClick(newsItem: ArticlesItem?)
    }

    class viewModel(val newsItem: NewsItemBinding) : RecyclerView.ViewHolder(newsItem.root) {
        fun bindView(item: ArticlesItem?) {
            newsItem.item = item
            newsItem.executePendingBindings()
        }
    }


}