package com.example.github.domain.repository

import com.example.github.data.models.GetUserProfileInfo
import com.example.github.data.models.RepositoryItem
import com.example.github.data.models.ResultData
import com.example.github.data.models.UserItems
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAccessToken(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfo>>

    suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<UserItems>>>

    suspend fun getUserRepositories(): Flow<ResultData<List<RepositoryItem>>>

    suspend fun searchRepositoriesByRepositoryName(repoName: String): Flow<ResultData<List<RepositoryItem>>>
}