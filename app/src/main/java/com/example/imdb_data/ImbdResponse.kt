package com.example.imdb_data

import com.google.gson.annotations.SerializedName

data class ImbdResponse(@SerializedName("Search") var response: ArrayList<Movie>)