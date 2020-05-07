package com.example.git

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class GithubUserAdapter() : RecyclerView.Adapter<GithubUserAdapter.GithubViewHolder>() {

       var githubUsers: ArrayList<GithubUser> = ArrayList()
       var onItemClick : ((login:String)->Unit)?=null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder =
                GithubViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false))

        override fun getItemCount() = githubUsers.size

        override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {

            holder?.bind(githubUsers[position])

        }

       fun swap(l: ArrayList<GithubUser>?)
       {
           githubUsers = l!!
           notifyDataSetChanged()

       }
       inner   class GithubViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

            fun bind(githubUser: GithubUser) = with(itemView) {
                tViewLogin?.text = githubUser.login
                tViewScore?.text = githubUser.score.toString()
                tViewUrl?.text = githubUser.html_url
                Picasso.get().load(githubUser.avatar_url).into(img)
                setOnClickListener {
                    (onItemClick!!)(githubUser.login)
                }

            }

        }
    }
