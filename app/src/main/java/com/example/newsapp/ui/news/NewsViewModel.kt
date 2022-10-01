package com.example.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.category.Category_item
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.models.ArticlesItemDTO
import domain.models.SourcesItemDTO
import domain.repos.news.NewsRepository
import domain.repos.source.SourcesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository, val sourcesRepository: SourcesRepository
) : ViewModel() {
    val API_KEY = "66c4c0a11a804109a8ea511820fd1d97"

    private val progressBarL = MutableLiveData(false)
    var progressBar: LiveData<Boolean> = progressBarL

    private val sourcesItemL = MutableLiveData<List<SourcesItemDTO?>?>()
    val sourcesItem: LiveData<List<SourcesItemDTO?>?> = sourcesItemL

    private val newsListL = MutableLiveData<List<ArticlesItemDTO?>?>()
    val newsList: LiveData<List<ArticlesItemDTO?>?> = newsListL

    private val toastMessageL = MutableLiveData<String>()
    val toastMessage: LiveData<String> = toastMessageL


//    lateinit var newsOnlineDataSource: NewsOnlineDataSource
//    lateinit var sourcesOnlineDataSource: SourcesOnlineDataSource
//    init {
//        newsOnlineDataSource=NewsOnlineDataSourceImpl(ApiManager.getApis())
//        sourcesOnlineDataSource=SourceOnlineDataSourceImpl(ApiManager.getApis())
//        newsRepository=NewsRepositoryImpl(newsOnlineDataSource)
//        sourcesRepository=SourcesRepositoryImpl(sourcesOnlineDataSource)
//
//    }


    // get news put in recycle view from api and search with it
    fun getNews(source: SourcesItemDTO? = null, q: String) {
        progressBarL.value = true

        viewModelScope.launch {
            try {
                progressBarL.value = false

                if (q.isNotEmpty()) {
                    val newsResponseSearch =
                        newsRepository.getNewsBySearch(API_KEY, source?.id!!, q)
//                        ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", q)
                    newsListL.value = newsResponseSearch
                } else {
                    val newsResponse = newsRepository.getNews(API_KEY, source?.id!!)
//                        ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", "")
                    newsListL.value = newsResponse

                }
            } catch (ex: Exception) {
                progressBarL.value = false
                toastMessageL.value = ex.localizedMessage

            }

        }

//        if (q.isNotEmpty()) {
//            ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", q)
//        } else {
//            ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", "")
//        }
//             .enqueue(
//                 object : Callback<NewsResponse> {
//
//                     override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                         Log.e("TAG", t.localizedMessage)
//                         progressBar.value=false
//                     }
//
//                     override fun onResponse(
//                         call: Call<NewsResponse>,
//                         response: Response<NewsResponse>
//                     ) {
//                         progressBar.value=false
//                         newsList.value=response.body()?.articles
//                     }
//                 }
//             )
//

    }

    //get news sources to put in tab layout from api
    fun getNewsSources(categorie: Category_item) {
        viewModelScope.launch {
            progressBarL.value = true

            try {
                val source = sourcesRepository.getSources(API_KEY, categorie.id)
                //                ApiManager.getApis().sources(constant.API_KEY, categorie.id)
                progressBarL.value = false
                sourcesItemL.value = source
            } catch (ex: Exception) {
                progressBarL.value = false
                toastMessageL.value = ex.localizedMessage

            }
        }
//        ApiManager.getApis().sources(constant.API_KEY, categorie.id).enqueue(
//            object : Callback<SourceResponse> {
//
//                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
//                    progressBar.value=false
//                    Log.e("TAG", t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<SourceResponse>,
//                    response: Response<SourceResponse>
//                ) {
//                    progressBar.value=false
//                    sourcesItem.value=response.body()?.sources
//                }
//            }
//
//        )
    }


}