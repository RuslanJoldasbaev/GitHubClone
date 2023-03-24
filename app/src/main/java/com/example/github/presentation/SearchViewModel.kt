package com.example.github.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.ResultData
import com.example.github.data.models.SearchRepositoriesByRepositoryName
import com.example.github.data.models.SearchUsersByUsername
import com.example.github.data.remote.GitHubApi
import com.example.github.data.remote.RetrofitHelper
import com.example.github.domain.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    val repo = MainRepository(RetrofitHelper.getInstance().create(GitHubApi::class.java))

    val searchUsersByUsernameFlow = MutableSharedFlow<SearchUsersByUsername>()
    val searchRepositoriesByRepositoryNameFlow =
        MutableSharedFlow<String>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun searchUsersByUsername(login: String) {
        repo.searchUsersByUsername(login).onEach {
            when (it) {
                is ResultData.Success -> {
                    searchUsersByUsernameFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchRepositoriesByRepositoryName(searchValue: String) {
        repo.searchRepositoriesByRepositoryName(searchValue).onEach {
            when (it) {
                is ResultData.Success -> {
                    searchRepositoriesByRepositoryNameFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}