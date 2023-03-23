package com.example.github.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.*
import com.example.github.data.remote.GitHubApi
import com.example.github.data.remote.RetrofitHelper
import com.example.github.domain.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repo = MainRepository(RetrofitHelper.getInstance().create(GitHubApi::class.java))

    val getUserProfileInfoFlow = MutableSharedFlow<GetUserProfileInfo>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<RepositoryItem>>()
    val searchUsersByUsernameFlow = MutableSharedFlow<SearchUsersByUsername>()
    val searchRepositoriesByRepositoryNameFlow = MutableSharedFlow<SearchRepositoriesByRepositoryName>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun getUserProfileInfo() {
        repo.getUserProfileInfo().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserProfileInfoFlow.emit(it.data)
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

    suspend fun getUserRepositories() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
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

    suspend fun searchUsersByUsername() {
        repo.searchUsersByUsername().onEach {
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

    suspend fun searchRepositoriesByRepositoryName() {
        repo.searchRepositoriesByRepositoryName().onEach {
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