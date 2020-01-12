package com.example.joeljacob_androidcodechallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.joeljacob_androidcodechallenge.R
import com.example.joeljacob_androidcodechallenge.model.Child
import kotlinx.android.synthetic.main.reddit_item_layout.view.*

class RedditAdapter(val post: List<Child>, var delegate: RedditDelgate): RecyclerView.Adapter<RedditAdapter.RedditViewHolder>() {
    private  lateinit var context : Context

    interface RedditDelgate {
        fun postItem(title : String , username : String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        context = parent.context.applicationContext

        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_item_layout, parent, false)
        return RedditViewHolder(view)
    }

    override fun getItemCount(): Int = post.size

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        holder.title.setText(post.get(position).data.title)
        holder.username.setText(post.get(position).data.author)
        holder.commentAmount.setText(post.get(position).data.numComments.toString() + " comments")
        holder.upsAmount.setText(post.get(position).data.ups.toString() + " ups")
        holder.downsAmount.setText(post.get(position).data.downs.toString() + " downs")

        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
            .load(post.get(position).data.thumbnail)
            .into(holder.thumbnail)

        val transition =
            AnimationUtils.loadAnimation(context, R.anim.transition_animation)
        holder.itemView.startAnimation(transition)

        holder.itemView.setOnClickListener {
            delegate.postItem(post.get(position).data.title, post.get(position).data.author)
        }
    }


    class RedditViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.item_title
        var username : TextView = itemView.item_username
        var commentAmount : TextView = itemView.item_comments
        var upsAmount : TextView = itemView.item_ups
        var downsAmount : TextView = itemView.item_downs
        var thumbnail : ImageView = itemView.item_thumbnail
    }
}