package com.example.githubclone.domain.usecase.impl

import com.example.github.data.models.GetUserProfileInfo
import com.example.github.data.models.RepositoryItem
import com.example.github.data.models.ResultData
import com.example.github.data.models.UserItems
import com.example.github.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.Flow

class MainUseCaseImpl(private val repo: MainRepository) : MainUseCase {
    override suspend fun getAccessToken(): Flow<ResultData<String>> {
        return repo.getAccessToken()
    }

    override suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfo>> = repo.getUserProfileInfo()

    override suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<UserItems>>> = repo.searchUsersByUsername(userName)

    override suspend fun getUserRepositories(): Flow<ResultData<List<RepositoryItem>>> = repo.getUserRepositories()

    override suspend fun searchRepositoriesByRepositoryName(repoName: String): Flow<ResultData<List<RepositoryItem>>> = repo.searchRepositoriesByRepositoryName(repoName)
}