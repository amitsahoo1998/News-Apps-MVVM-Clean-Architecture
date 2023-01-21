package dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import dev.amits.cleanarchitecturenewsapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveNewsViewModel @Inject constructor(
    private val getNewsUseCases: GetNewsUseCases,
    private val deleteNewsUseCases: DeleteNewsUseCases,
    private val getSavedNewsUseCases: GetSavedNewsUseCases,
    private val getSearchNewsUseCases: GetSearchNewsUseCases,
    private val saveNewsUseCases: SaveNewsUseCases
):ViewModel() {
    val topHeadingData : MutableLiveData<dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse>?> = MutableLiveData()

    val coroutineExceptionHandler = CoroutineExceptionHandler{_ , throwable-> throwable.printStackTrace()}

    //Remote data
    fun searchNews(query : String){
        topHeadingData.value = null
        CoroutineScope(Dispatchers.IO).launch {
            topHeadingData.postValue(getSearchNewsUseCases.execute("in",query,1))
        }
    }

    fun getNewsHeadlines(country:String , page : Int) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        topHeadingData.postValue(dev.amits.cleanarchitecturenewsapp.data.Resource.Loading())
        topHeadingData.postValue(getNewsUseCases.execute(page, country))
    }

    //Local data
    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCases.execute(article)
    }

    fun getSaveNews() = liveData {
        getSavedNewsUseCases.execute().collect{
            emit(it)
        }
    }
    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteNewsUseCases.execute(article)
    }
}