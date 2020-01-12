package com.example.joeljacob_androidcodechallenge.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joeljacob_androidcodechallenge.R
import com.example.joeljacob_androidcodechallenge.adapter.RedditAdapter
import com.example.joeljacob_androidcodechallenge.model.Child
import com.example.joeljacob_androidcodechallenge.viewmodel.RedditViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RedditAdapter.RedditDelgate {

    val compositeDisposable = CompositeDisposable()
    lateinit var viewModel : RedditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(RedditViewModel::class.java)
        getRedditPosts("funny")
        search_view.setQuery("funny", false)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        recycler_view.addItemDecoration(decorator)

        search_button.setOnClickListener {
            search()
        }

    }

    private fun getRedditPosts(subreddit : String){
        compositeDisposable.add(
            viewModel.getRedditPosts(subreddit).subscribe{redditPost->
                setRV(redditPost.data.children)
            }
        )
    }

    private fun setRV(posts:List<Child>){
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RedditAdapter(posts,this)

    }

    override fun postItem(title: String, username: String) {
        val message = "Check out what " + username + " said on Reddit: \"" +title + "\""

        Log.d("TAG_X", message)

        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try{
            startActivity(Intent.createChooser(mIntent, ""))
        }catch (e : Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun search(){
        getRedditPosts(search_view.query.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
