package com.example.github.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.*
import com.example.githubclone.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(private val useCase: MainUseCase) : ViewModel() {

    val searchUsersByUsernameFlow = MutableSharedFlow<List<UserItems>>()
    val searchRepositoriesByRepositoryNameFlow =
        MutableSharedFlow<List<RepositoryItem>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun searchUsersByUsername(login: String) {
        useCase.searchUsersByUsername(login).onEach {
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

    suspend fun searchRepositoriesByRepositoryName(name: String) {
        useCase.searchRepositoriesByRepositoryName(name).onEach {
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