package com.example.github.domain

import com.example.github.data.models.ResultData
import com.example.github.data.remote.GitHubApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(val api: GitHubApi) {

    suspend fun getUserProfileInfo() = flow {
        val token = "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"
        val response = api.getUserProfileInfo(token)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun getUserRepositories() = flow {
        val token = "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"
        val response = api.getUserRepositories(token)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun searchUsersByUsername() = flow {
        val token = "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"

        val response = api.searchUsersByUsername(token)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun searchRepositoriesByRepositoryName() = flow {
        val token = "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"

        val response = api.searchRepositoriesByRepositoryName(token)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}