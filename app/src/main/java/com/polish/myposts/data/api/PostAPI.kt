package com.polish.myposts.data.api

import com.polish.myposts.constants.URLConstants
import com.polish.myposts.constants.URLEndpoints
import com.polish.myposts.model.POST
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostAPI {

    @GET(URLEndpoints.POST_ENDPOINT)
   suspend fun viewPosts():List<POST>


    companion object {

        operator fun invoke():PostAPI {

            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URLConstants.BASE_URL)
                .client(client)
                .build()
                .create(PostAPI::class.java)

        }

    }

}