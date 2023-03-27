package com.example.github.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.*
import com.example.githubclone.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {

    val getUserProfileInfoFlow = MutableSharedFlow<GetUserProfileInfo>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<RepositoryItem>>()
    val getAccessTokenFlow = MutableSharedFlow<String>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun getUserProfileInfo() {
        useCase.getUserProfileInfo().onEach {
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
        useCase.getUserRepositories().onEach {
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

    suspend fun getAccessToken() {
        useCase.getAccessToken().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAccessTokenFlow.emit(it.data)
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