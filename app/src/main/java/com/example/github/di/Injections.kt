package com.example.github.di

import com.example.github.data.remote.GitHubApi
import com.example.github.domain.MainRepository
import com.example.github.presentation.MainViewModel
import com.example.github.presentation.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<MainRepository> {
        MainRepository(api = get())
    }

    single<Retrofit> {
        val httpLogginInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        val interceptor = GitHubInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLogginInterceptor)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(client)
            .build()
        retrofit

    }

    single<GitHubApi> {
        provideApi(retrofit = get())
    }

}

val viewModelModule = module {
    single<MainViewModel> {
        MainViewModel(repo = get())
    }

    single<SearchViewModel> {
        SearchViewModel(repo = get())
    }
}

fun provideApi(retrofit: Retrofit): GitHubApi {
    return retrofit.create(GitHubApi::class.java)
}