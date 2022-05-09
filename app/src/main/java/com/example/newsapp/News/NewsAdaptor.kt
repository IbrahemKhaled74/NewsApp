package com.example.newsapp.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.apiResponses.ArticlesItem

class newsAdaptor(var newsItem: MutableList<ArticlesItem?>?) :
    RecyclerView.Adapter<newsAdaptor.viewModel>() {
    fun changeData(newsItem: MutableList<ArticlesItem?>?) {
        this.newsItem = newsItem
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return viewModel(view)
    }

    override fun onBindViewHolder(holder: viewModel, position: Int) {
        val item = newsItem?.get(position)
        holder.newstitle.text = item?.source?.name
        holder.newsDesc.text = item?.description
        holder.newsDate.text = item?.publishedAt
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.newsImage)
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


    class viewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.news_image)
        val newstitle: TextView = itemView.findViewById(R.id.news_name)
        val newsDesc: TextView = itemView.findViewById(R.id.news_desc)
        val newsDate: TextView = itemView.findViewById(R.id.news_author)
    }

//    override fun getFilter(): Filter {
//        return filterItem
//    }
//
//    private val filterItem:Filter=object :Filter(){
//        override fun performFiltering(constrain: CharSequence?): FilterResults {
//            var filteredList=ArrayList<ArticlesItem?>()
//            if(constrain==null){
//                filteredList.addAll(newsItemFull!!)
//            }else{
//                val filterPatern:String=constrain.toString().lowercase()
//                for (item:ArticlesItem? in newsItemFull!!){
//                    if (item?.title?.contains(filterPatern)!!){
//                        filteredList.add(item)
//                    }
//                }
//            }
//            val results=FilterResults()
//            results.values=filteredList
//            return results
//        }
//
//        override fun publishResults(constrain: CharSequence?, filterResult: FilterResults?) {
//            newsItem?.clear()
//            newsItem?.addAll(filterResult?.values as List<ArticlesItem>)
//            notifyDataSetChanged()
//
//        }
//    }

}