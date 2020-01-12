package com.example.joeljacob_androidcodechallenge.network

import com.example.joeljacob_androidcodechallenge.model.RedditPojo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RedditRetrofit {
    val BASE_URL = "https://www.reddit.com"

    private lateinit var redditService : RedditService

    init {
        redditService = createService(retrofitInstance())
    }

    private fun retrofitInstance():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit):RedditService{
        return retrofit.create(RedditService::class.java)
    }

    fun getRedditPosts(subreddit : String) : Observable<RedditPojo>{
        return redditService.getRedditPosts(subreddit)
    }
}