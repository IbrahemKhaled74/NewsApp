package com.example.newsapp.News

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.API.ApiManager
import com.example.newsapp.NewsDetails
import com.example.newsapp.R
import com.example.newsapp.apiResponses.ArticlesItem
import com.example.newsapp.apiResponses.NewsResponse
import com.example.newsapp.apiResponses.SourceResponse
import com.example.newsapp.apiResponses.SourcesItem
import com.example.newsapp.category.category_item
import com.example.newsapp.constant
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class News : Fragment() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var newsRecyclerView: RecyclerView
    lateinit var newsAdaptor: newsAdaptor
    lateinit var categorie: category_item
    lateinit var call: Call<NewsResponse>
    lateinit var searchView: androidx.appcompat.widget.SearchView
    lateinit var newsTitle: TextView


    companion object {
        fun getInstance(categorie: category_item): News {
            val fragment = News()
            fragment.categorie = categorie
            return fragment
        }

        var items: ArticlesItem? = null
        var newsList: MutableList<ArticlesItem?>? = null
        var source: SourcesItem? = null

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initView()
        getNewsSources()
        onTabSelected()

    }

    private fun initView() {
        tabLayout = requireActivity().findViewById(R.id.tabLayout)
        progressBar = requireActivity().findViewById(R.id.progress_bar)
        newsRecyclerView = requireActivity().findViewById(R.id.news_recycle)
        searchView = requireActivity().findViewById(R.id.searchView)
        newsTitle = requireActivity().findViewById(R.id.AppName)
        newsAdaptor = newsAdaptor(null)
        newsRecyclerView.adapter = newsAdaptor
        newsAdaptor.onItemSelectedListener = object : newsAdaptor.OnItemSelectedListener {
            override fun onNewsClick(newsItem: ArticlesItem?) {
                items = newsItem
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_Container, NewsDetails())
                    ?.addToBackStack("")
                    ?.commit()
            }
        }
        // here when i search i will call getNews and send it what i would search about
        searchView.isVisible = true
        searchView.setOnSearchClickListener {
            newsTitle.text = ""
        }
        searchView.setOnCloseListener {
            newsTitle.text = "NewsApp"
            return@setOnCloseListener false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(p0: String?): Boolean {
                getNews(source, p0?.lowercase()!!)


                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                getNews(source, p0?.lowercase()!!)

                return false
            }
        })
    }

    // when tab selected what will show
    fun onTabSelected() {
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    source = tab?.tag as SourcesItem
                    getNews(source, "")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    getNews(source, "")

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    getNews(source, "")
                }
            }
        )

    }

    // get news put in recycle view from api and search with it
    private fun getNews(source: SourcesItem? = null, q: String) {


        call = if (q.isNotEmpty()) {
            ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", q)
        } else {
            ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", "")
        }

        call
            .enqueue(
                object : Callback<NewsResponse> {
                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Log.e("TAG", t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        newsList = response.body()?.articles
                        newsAdaptor.changeData(newsList)
                    }
                }
            )
    }

    //get news sources to put in tab layout from api
    private fun getNewsSources() {
        ApiManager.getApis().sources(constant.API_KEY, categorie.id).enqueue(
            object : Callback<SourceResponse> {
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressBar.isVisible = false
                    Log.e("TAG", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                    progressBar.isVisible = false
                    addSourcesTabs(response.body()?.sources)
                }
            }

        )
    }

    // add sources in tab layout
    private fun addSourcesTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { source ->
            val tab = tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            tabLayout.addTab(tab)

        }

    }


}