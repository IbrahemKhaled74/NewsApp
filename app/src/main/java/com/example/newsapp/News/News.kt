package com.example.newsapp.News

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.NewsDetails
import com.example.newsapp.R
import com.example.newsapp.apiResponses.ArticlesItem
import com.example.newsapp.apiResponses.SourcesItem
import com.example.newsapp.category.Category_item
import com.example.newsapp.databinding.NewsFragmentBinding
import com.google.android.material.tabs.TabLayout


class News : Fragment() {
    lateinit var newsAdaptor: newsAdaptor
    lateinit var categorie: Category_item

    companion object {
        fun getInstance(categorie: Category_item): News {
            val fragment = News()
            fragment.categorie = categorie
            return fragment
        }

        var items: ArticlesItem? = null

    }

    var source: SourcesItem? = null
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsDataBinding: NewsFragmentBinding
    lateinit var searchView: androidx.appcompat.widget.SearchView
    lateinit var AppName: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)

        return newsDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeData()
        onTabSelected()
    }

    private fun subscribeData() {
        newsViewModel.getNewsSources(categorie)
        newsViewModel.progressBar.observe(viewLifecycleOwner, {
            newsDataBinding.progressBar.isVisible = it
        })
        newsViewModel.newsList.observe(viewLifecycleOwner, {
            showNews(it)

        })
        newsViewModel.sourcesItem.observe(viewLifecycleOwner, {
            addSourceTab(it)

        })
        newsViewModel.toastMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }


    private fun initView() {
        newsAdaptor = newsAdaptor(null)
        newsDataBinding.newsRecycle.adapter = newsAdaptor
        searchView = requireActivity().findViewById(R.id.searchView)
        AppName = requireActivity().findViewById(R.id.AppName)
        newsAdaptor.onItemSelectedListener = object : newsAdaptor.OnItemSelectedListener {
            override fun onNewsClick(newsItem: ArticlesItem?) {
                items = newsItem
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_Container, NewsDetails())
                    ?.addToBackStack("")
                    ?.commit()
            }
        }
        searchView.isVisible = true
        searchView.setOnSearchClickListener {
            AppName.text = ""
        }
        searchView.setOnCloseListener {
            AppName.text = "NewsApp"
            return@setOnCloseListener false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(p0: String?): Boolean {
                newsViewModel.getNews(source, p0?.lowercase()!!)
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                newsViewModel.getNews(source, p0?.lowercase()!!)

                return false
            }
        })


    }

    private fun showNews(newsItem: List<ArticlesItem?>?) {
        newsAdaptor.changeData(newsItem)
    }

    // when tab selected what will show
    private fun onTabSelected() {
        newsDataBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    source = tab?.tag as SourcesItem
                    newsViewModel.getNews(source, "")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    newsViewModel.getNews(source, "")

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    newsViewModel.getNews(source, "")
                }
            }
        )

    }

    // add tab in tab Layout
    private fun addSourceTab(sources: List<SourcesItem?>?) {
        sources?.forEach { source ->
            val tab = newsDataBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            newsDataBinding.tabLayout.addTab(tab)

        }
    }


}