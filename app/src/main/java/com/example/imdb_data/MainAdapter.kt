package com.example.imdb_data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_row.view.*

//val homeFeed: HomeFeed
class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val movieTitles = listOf("first", "second", "third", "4th", "5th", "how", "i gonna", "success", "this??")

    //    number of items
    override fun getItemCount(): Int {
        return homeFeed.movies.count()
//        return movieTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val movieTitles = movieTitles[position]
//        holder?.view?.textMovieTitle?.text = movieTitles
        val movie = homeFeed.movies[position]
        holder?.view?.textMovieTitle?.text = movie.title
        holder?.view?.textMovieYear?.text = movie.year.toString()
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}