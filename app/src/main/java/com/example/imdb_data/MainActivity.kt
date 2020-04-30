package com.example.imdb_data

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fetchData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_row.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var data: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = findViewById<TextView>(R.id.textInfo)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = MainAdapter()

        val buttonInfo = findViewById<Button>(R.id.buttonInfo)

//        buttonInfo.setOnClickListener {
//            val url = "http://www.omdbapi.com/?s=batman&genres=action&apikey=dbbca5c8"
//            val request = Request.Builder().url(url).build()
//            val client = OkHttpClient()
//
//            client.newCall(request).enqueue(object : Callback {
//                override fun onResponse(call: Call, response: Response) {
//                    Log.d("try", response.body?.string())
//
//                    val gson = GsonBuilder().create()
//                    val homeFeed =
//                        gson.fromJson(response.body?.string(), HomeFeed::class.java)
//
//                    runOnUiThread {
//                        recyclerView.adapter = MainAdapter(homeFeed)
//                        fetchData().execute()
//                    }
//                }
//
//                override fun onFailure(call: Call, e: IOException) {
//                    Log.d("try", "Failed to execute request")
//                }
//            })
//        }
        fetchJson()
    }

        fun fetchJson() {
        val url = "http://www.omdbapi.com/?s=batman&genres=action&apikey=dbbca5c8"

                val request = Request.Builder().url(url).build()

                val client = OkHttpClient()
                client.newCall(request).enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        Log.d("try", response.body?.string())

                        val gson = GsonBuilder().create()

                        val homeFeed =
                            gson.fromJson(response.body?.string(), HomeFeed::class.java)

                        runOnUiThread {
                            recyclerView.adapter = MainAdapter(homeFeed)
                            fetchData().execute()
                        }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("try", "Failed to execute request")
            }
        })
    }
    public fun getTextView(): TextView? {
        return data
    }
}

class HomeFeed(val movies: List<Movie>)

class Movie(val title: String, val year: Int)

