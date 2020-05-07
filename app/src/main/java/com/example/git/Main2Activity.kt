package com.example.git

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.git.Client
import com.example.git.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val id = intent.getStringExtra("ID")


        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){ Client.api.getUserById(id) }
            if(response.isSuccessful){
                response.body()?.let {
                    tViewLogin?.text = it.login
                    tViewScore?.text = it.score.toString()
                    tViewUrl?.text = it.html_url
                    Picasso.get().load(it.avatar_url).into(img);
                }
            }
        }
    }
}