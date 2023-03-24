package com.example.github.data.remote

import com.example.github.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface GitHubApi {

    @GET("{{BASE_URL}}/user")
    @FormUrlEncoded
    suspend fun getUserProfileInfo(@Header("Authorization") token: String): Response<GetUserProfileInfo>

    @GET("{{BASE_URL}}/user/repos")
    @FormUrlEncoded
    suspend fun getUserRepositories(@Header("Authorization") token: String): Response<List<RepositoryItem>>

    @GET("{{BASE_URL}}/search/users?q=yourtext")
    @FormUrlEncoded
    suspend fun searchUsersByUsername(
        @Query("login") login: String
    ): Response<SearchUsersByUsername>

    @GET("{{BASE_URL}}/search/repositories?q=Chat App")
    @FormUrlEncoded
    suspend fun searchRepositoriesByRepositoryName(
        @Query("name") name: String
    ): Response<SearchRepositoriesByRepositoryName>

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String
    ): Response<TokenResponseData>

}