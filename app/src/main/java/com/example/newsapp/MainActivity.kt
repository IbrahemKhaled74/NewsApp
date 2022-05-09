//package com.example.newsapp
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.ProgressBar
//import androidx.core.view.isVisible
//import androidx.recyclerview.widget.RecyclerView
//import com.example.newsapp.API.ApiManager
//import com.example.newsapp.apiResponses.NewsResponse
//import com.example.newsapp.apiResponses.SourceResponse
//import com.example.newsapp.apiResponses.SourcesItem
//import com.google.android.material.tabs.TabLayout
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//class MainActivity : AppCompatActivity() {
//    lateinit var tabLayout: TabLayout
//    lateinit var progressBar: ProgressBar
//    lateinit var newsRecyclerView: RecyclerView
//    lateinit var newsAdaptor: newsAdaptor
//        override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initView()
//        getNewsSources()
//        onTabSelected()
//
//    }
//    private fun initView(){
//        tabLayout=findViewById(R.id.tabLayout)
//        progressBar=findViewById(R.id.progress_bar)
//        newsRecyclerView=findViewById(R.id.news_recycle)
//        newsAdaptor=newsAdaptor(null)
//        newsRecyclerView.adapter=newsAdaptor
//
//    }
//    private fun onTabSelected(){
//        tabLayout.addOnTabSelectedListener(
//            object :TabLayout.OnTabSelectedListener{
//                override fun onTabSelected(tab: TabLayout.Tab?) {
//                    val source=tab?.tag as SourcesItem
//                    getNews(source)
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab?) {
//                }
//
//                override fun onTabReselected(tab: TabLayout.Tab?) {
//                    val source=tab?.tag as SourcesItem
//                    getNews(source)
//                }
//            }
//        )
//
//    }
//    private fun getNews(source:SourcesItem) {
//        ApiManager.getApis().news(constant.API_KEY,source.id?:"")
//            .enqueue(
//                object:Callback<NewsResponse>{
//                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                        Log.e("TAG", t.localizedMessage)
//                    }
//
//                    override fun onResponse(
//                        call: Call<NewsResponse>,
//                        response: Response<NewsResponse>
//                    ) {
//                        newsAdaptor.changeData(response.body()?.articles )
//
//                    }
//                }
//            )
//    }
//    private fun getNewsSources(){
//        ApiManager.getApis().sources(constant.API_KEY,"").enqueue(
//            object : Callback<SourceResponse> {
//                override fun onFailure(call: Call<SourceResponse>, t: Throwable)
//                {
//                    progressBar.isVisible=false
//                    Log.e("TAG", t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<SourceResponse>,
//                    response: Response<SourceResponse>
//                ) {
//                    progressBar.isVisible=false
//                    addSourcesTabs(response.body()?.sources)
//                }
//            }
//
//        )
//    }
//    private fun addSourcesTabs(sources: List<SourcesItem?>?) {
//        sources?.forEach {
//            source->
//            val tab=tabLayout.newTab()
//            tab.setText(source?.name)
//            tab.tag=source
//            tabLayout.addTab(tab)
//
//        }
//
//    }
//}
//
//
//
