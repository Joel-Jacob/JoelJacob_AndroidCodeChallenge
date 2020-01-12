package com.example.joeljacob_androidcodechallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.joeljacob_androidcodechallenge.model.RedditPojo
import com.example.joeljacob_androidcodechallenge.network.RedditRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RedditViewModel(application : Application) : AndroidViewModel(application) {
    val redditRetrofit = RedditRetrofit()

    fun getRedditPosts(subreddit : String):Observable<RedditPojo>{
        return redditRetrofit
            .getRedditPosts(subreddit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}