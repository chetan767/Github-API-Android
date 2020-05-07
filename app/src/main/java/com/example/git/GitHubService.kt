package com.example.git

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("users")
    suspend fun getUsers(): Response<ArrayList<GithubUser>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id")id:String): Response<GithubUser>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")query: String): Response<userResponse>


}