package com.example.github.data.remote

import com.example.github.data.models.GetUserProfileInfo
import com.example.github.data.models.RepositoryItem
import com.example.github.data.models.SearchRepositoriesByRepositoryName
import com.example.github.data.models.SearchUsersByUsername
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GitHubApi {

    @GET("{{BASE_URL}}/user")
    suspend fun getUserProfileInfo(@Header("Authorization") token: String): Response<GetUserProfileInfo>

    @GET("{{BASE_URL}}/user/repos")
    suspend fun getUserRepositories(@Header("Authorization") token: String): Response<List<RepositoryItem>>

    @GET("{{BASE_URL}}/search/users?q=yourtext")
    suspend fun searchUsersByUsername(@Header("Authorization") token: String): Response<SearchUsersByUsername>

    @GET("{{BASE_URL}}/search/repositories?q=Chat App")
    suspend fun searchRepositoriesByRepositoryName(@Header("Authorization") token: String): Response<SearchRepositoriesByRepositoryName>
}