package br.com.themovies.model.response.detailsmovie


import com.google.gson.annotations.SerializedName

data class DetailsMovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<GenreResponse>,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("revenue") val revenue: Int,
)