package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapp.News.News

class NewsDetails() : Fragment() {
    lateinit var imageView: ImageView
    lateinit var author: TextView
    lateinit var title: TextView
    lateinit var time: TextView
    lateinit var desc: TextView
    lateinit var viewArticle: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.image_details)
        author = view.findViewById(R.id.source_text)
        title = view.findViewById(R.id.news_title)
        time = view.findViewById(R.id.news_time)
        desc = view.findViewById(R.id.news_details_desc)
        viewArticle = view.findViewById(R.id.view_article)

        author.text = News.items?.source?.name
        title.text = News.items?.title
        time.text = News.items?.publishedAt
        desc.text = News.items?.content
        Glide.with(view).load(News.items?.urlToImage)
            .into(imageView)
        viewArticle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(News.items?.url))
            startActivity(intent)

        }


    }

}