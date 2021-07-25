package br.com.themovies.remote

import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {

    @GET("movie/upcoming")
    suspend fun getUpCommingMovies(
        @Query("api_key") apiKey: String = "",
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int,
        @Query("region") region: String = "BR"
    ): Response<List<ResultResponse>>

    @GET("movie/")
    suspend fun getDetailsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "",
        @Query("language") language: String = "pt-BR"
    ): Response<DetailsMovieResponse>
}