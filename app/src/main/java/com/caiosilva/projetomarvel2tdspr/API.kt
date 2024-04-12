package com.caiosilva.projetomarvel2tdspr

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("/posts")
    fun getPosts(): Call<List<Posts>>

    @POST("/posts")
    fun submitPosts(@Body newPost: NewPost): Call<Posts>
}