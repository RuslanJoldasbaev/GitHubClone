package com.example.github.domain

import com.example.github.data.local.LocalStorage
import com.example.github.data.models.ResultData
import com.example.github.data.remote.GitHubApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(val api: GitHubApi) {

    suspend fun getUserProfileInfo() = flow {
        val response = api.getUserProfileInfo("Bearer ${LocalStorage().token}")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun getUserRepositories() = flow {
        val response = api.getUserRepositories("Bearer ${LocalStorage().token}")
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun searchUsersByUsername(login: String) = flow {
        val response = api.searchUsersByUsername(login)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun searchRepositoriesByRepositoryName(searchValue: String) = flow {
        val response = api.searchRepositoriesByRepositoryName(searchValue)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.items[0].name))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun getAccessToken(code: String) = flow {
        val client_id = "8f3cf5f09bd0c93a0528"
        val client_secret = "5447af3efb5afba3751aa6a0025e97affcf1a538"
        val response = api.getAccessToken(client_id, client_secret, code)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

}