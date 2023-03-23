package com.example.github.data.models

data class SearchRepositoriesByRepositoryName(
    val total_count:Int,
    val incomplete_results:Boolean,
    val items: List<RepositoryItem>
)
