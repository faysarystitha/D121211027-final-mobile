package com.D121211027.newsest.ui.activities

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211027.newsest.NewsApplication
import com.D121211027.newsest.data.models.Article
import com.D121211027.newsest.data.repository.NewsRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val news: List<Article>) : MainUiState
    object Error : MainUiState
    object Loading :MainUiState
}

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getNews() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = newsRepository.getNews()
            Log.d("MainViewModel", "getNews: ${result.data}")
            mainUiState =MainUiState.Success(result.data.orEmpty())
        } catch(e: IOException) {
            Log.d("MainViewModel", "getNews error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getNews()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsApplication)
                val newsRepository = application.container.newsRepository
                MainViewModel(newsRepository)
            }
        }
    }
}