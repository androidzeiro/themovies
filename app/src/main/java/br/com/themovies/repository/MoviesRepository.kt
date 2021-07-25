package br.com.themovies.repository

import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import com.jhonata.catapp.model.ResponseDTO
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getUpCommingMovies(page: Int): Flow<ResponseDTO<out List<ResultResponse>?>>
    fun getDetailsMovie(movieId: Int): Flow<ResponseDTO<out DetailsMovieResponse?>>
}