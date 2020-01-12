package com.example.joeljacob_androidcodechallenge.network

import com.example.joeljacob_androidcodechallenge.model.RedditPojo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditService {
    //https://www.reddit.com/r/funny/.json
    @GET("/r/{subreddit}/.json")
    fun getRedditPosts(
        @Path("subreddit") subreddit : String): Observable<RedditPojo>
}