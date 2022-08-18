package com.example.newsapp.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding

class NewsDetails : Fragment() {
    lateinit var newsDetails: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsDetails =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        return newsDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsDetails.sourceText.text = News.items?.source?.name
        newsDetails.newsTitle.text = News.items?.title
        newsDetails.newsTime.text = News.items?.publishedAt
        newsDetails.newsDetailsDesc.text = News.items?.content
        Glide.with(view)
            .load(News.items?.urlToImage)
            .into(newsDetails.imageDetails)

        newsDetails.viewArticle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(News.items?.url))
            startActivity(intent)

        }


    }

}