package com.example.newsapp.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.API.ApiManager
import com.example.newsapp.apiResponses.ArticlesItem
import com.example.newsapp.apiResponses.SourcesItem
import com.example.newsapp.category.Category_item
import com.example.newsapp.constant
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val progressBar = MutableLiveData(false)
    val sourcesItem = MutableLiveData<List<SourcesItem?>?>()
    val newsList = MutableLiveData<List<ArticlesItem?>?>()
    val toastMessage = MutableLiveData<String>()


    // get news put in recycle view from api and search with it
    fun getNews(source: SourcesItem? = null, q: String) {
        progressBar.value = true

        viewModelScope.launch {
            try {
                progressBar.value = false

                if (q.isNotEmpty()) {
                    val newsResponseSearch =
                        ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", q)
                    newsList.value = newsResponseSearch.articles
                } else {
                    val newsResponse =
                        ApiManager.getApis().news(constant.API_KEY, source?.id ?: "", "")
                    newsList.value = newsResponse.articles

                }
            } catch (ex: Exception) {
                progressBar.value = false
                toastMessage.value = ex.localizedMessage

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
            progressBar.value = true

            try {
                val source = ApiManager.getApis().sources(constant.API_KEY, categorie.id)
                progressBar.value = false
                sourcesItem.value = source.sources
            } catch (ex: Exception) {
                progressBar.value = false
                toastMessage.value = ex.localizedMessage

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