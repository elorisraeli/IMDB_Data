package com.example.imdb_data

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    private fun fetchJson() {
        val url = "https://www.omdbapi.com/?s=batman&genres=action&apikey=dbbca5c8"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body
                if (responseBody != null) {
                    val gson = GsonBuilder().create()
                    val homeFeed = gson.fromJson(responseBody.string(), ImbdResponse::class.java)
                    runOnUiThread {
                        val mainAdapter = MainAdapter(homeFeed)
                        recyclerView.adapter = mainAdapter
                        mainAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("try", "Failed to execute request")
            }
        })
    }
}