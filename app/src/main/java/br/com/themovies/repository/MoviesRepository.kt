package br.com.themovies.repository

import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.model.response.upcomingmovies.UpComingMoviesResponse
import br.com.themovies.model.ResponseDTO
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getUpCommingMovies(page: Int): Flow<ResponseDTO<out UpComingMoviesResponse?>>
    fun getDetailsMovie(movieId: Int): Flow<ResponseDTO<out DetailsMovieResponse?>>
}