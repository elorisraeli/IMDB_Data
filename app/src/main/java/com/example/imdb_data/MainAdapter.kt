package com.example.imdb_data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MainAdapter(val moviesFeed: ImbdResponse) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return moviesFeed.response.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = moviesFeed.response[position]
        holder.view.textMovieTitle?.text = movie.title
        holder.view.textMovieYear?.text = movie.year.toString()

        Picasso.get()
            .load(movie.poster)
            .into(holder.view.imageView)
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}