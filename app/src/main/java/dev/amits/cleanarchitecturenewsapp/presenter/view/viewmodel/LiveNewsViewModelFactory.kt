package dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.amits.cleanarchitecturenewsapp.domain.usecases.*

class LiveNewsViewModelFactory(
    private val getNewsUseCases: GetNewsUseCases,
    private val deleteNewsUseCases: DeleteNewsUseCases,
    private val getSavedNewsUseCases: GetSavedNewsUseCases,
    private val getSearchNewsUseCases: GetSearchNewsUseCases,
    private val saveNewsUseCases: SaveNewsUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LiveNewsViewModel(getNewsUseCases,
        deleteNewsUseCases,getSavedNewsUseCases, getSearchNewsUseCases, saveNewsUseCases) as T
    }
}