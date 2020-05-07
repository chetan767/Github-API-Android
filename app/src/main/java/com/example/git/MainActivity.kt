package com.example.git

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val TAG : String = "xyz"
    val adapter = GithubUserAdapter();
    val originalList: ArrayList<GithubUser> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this);
        val decoration: DividerItemDecoration = DividerItemDecoration(baseContext, VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = this.adapter
        adapter.onItemClick = {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("ID", it)
            startActivity(intent)
        }
        GlobalScope.launch(Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) { Client.api.getUsers() }
            if (response.isSuccessful) {
                response.body()?.let {

                    originalList.addAll(it)
                    adapter.swap(it)

                }
            }
        }

        GlobalScope.launch(Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) { Client.api.getUsers() }
            if (response.isSuccessful) {
                response.body()?.let {


                    adapter.swap(it)

                }
            }
        }

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {

                    searchUsers(it)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {

                    searchUsers(it)
                }

                return true
            }

        })

        searchView.setOnCloseListener {
            adapter.swap(originalList)
            true
        }

    }


    fun searchUsers(query: String) {

        GlobalScope.launch(Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) { Client.api.searchUsers(query) }
            if (response.isSuccessful) {
                response.body()?.let {

                    adapter.swap(it.items)
                    Log.d(TAG,response.toString())


                }
            }

        }
    }

}
