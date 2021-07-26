package br.com.themovies.model.response.upcomingmovies


import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double
)