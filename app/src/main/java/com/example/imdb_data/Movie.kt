package com.example.imdb_data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    var title: String? = null,
    @SerializedName("Year")
    var year: String? = null,
    @SerializedName("Poster")
    var poster: String)
