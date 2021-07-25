package br.com.themovies.model.response.upcomingmovies


import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)