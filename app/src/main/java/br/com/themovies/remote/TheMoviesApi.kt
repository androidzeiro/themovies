package br.com.themovies.remote

import br.com.themovies.BuildConfig
import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.model.response.upcomingmovies.UpComingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = BuildConfig.API_KEY

interface TheMoviesApi {

    @GET("movie/upcoming")
    suspend fun getUpCommingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int,
        @Query("region") region: String = "BR"
    ): Response<UpComingMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "pt-BR"
    ): Response<DetailsMovieResponse>
}