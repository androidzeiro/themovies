package br.com.themovies.model.response.upcomingmovies


import com.google.gson.annotations.SerializedName

data class UpComingMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ResultResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)