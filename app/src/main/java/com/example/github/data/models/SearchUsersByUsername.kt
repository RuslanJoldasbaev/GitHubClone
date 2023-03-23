package com.example.github.data.models

data class SearchUsersByUsername(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserItems>
)
