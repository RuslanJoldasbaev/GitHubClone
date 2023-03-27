package com.example.github.domain.repository

import com.example.github.data.local.LocalStorage
import com.example.github.data.models.ResultData
import com.example.github.data.remote.GitHubApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(val api: GitHubApi) : MainRepository {

    override suspend fun getUserProfileInfo() = flow {
        val response = api.getUserProfileInfo()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    override suspend fun getUserRepositories() = flow {
        val response = api.getUserRepositories()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    override suspend fun searchUsersByUsername(login: String) = flow {
        val response = api.searchUsersByUsername(login)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.items))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    override suspend fun searchRepositoriesByRepositoryName(name: String) = flow {
        val response =
            api.searchRepositoriesByRepositoryName(name)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.items))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    override suspend fun getAccessToken() = flow {
        val client_id = "8f3cf5f09bd0c93a0528"
        val client_secret = "5447af3efb5afba3751aa6a0025e97affcf1a538"
        val response = api.getAccessToken(client_id, client_secret, LocalStorage().code)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.access_token))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

}