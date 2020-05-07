package com.example.git

data class userResponse(
        val totalCount: Int? = null,
        val incompleteResults: Boolean? = null,
        val items: ArrayList<GithubUser>? = null
)