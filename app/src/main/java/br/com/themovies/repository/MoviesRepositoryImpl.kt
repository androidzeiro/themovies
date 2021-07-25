package br.com.themovies.repository

import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import br.com.themovies.remote.TheMoviesApi
import com.jhonata.catapp.model.ResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val api: TheMoviesApi
) : MoviesRepository {
    override fun getUpCommingMovies(page: Int) = flow {
        emit(ResponseDTO.loading<List<ResultResponse>>())
        try {
            val result = api.getUpCommingMovies(page = page)
            result.run {
                if (isSuccessful) {
                    emit(ResponseDTO.success(body()))
                } else {
                    emit(
                        ResponseDTO.error<List<ResultResponse>>(
                            message = message(),
                            code = code()
                        )
                    )
                }
            }

        } catch (e: Exception) {
            emit(ResponseDTO.error<List<ResultResponse>>(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getDetailsMovie(movieId: Int) = flow {
        emit(ResponseDTO.loading<DetailsMovieResponse>())
        try {
            val result = api.getDetailsMovie(movieId = movieId)
            result.run {
                if (isSuccessful) {
                    emit(ResponseDTO.success(body()))
                } else {
                    emit(
                        ResponseDTO.error<DetailsMovieResponse>(
                            message = message(),
                            code = code()
                        )
                    )
                }
            }

        } catch (e: Exception) {
            emit(ResponseDTO.error<DetailsMovieResponse>(e))
        }
    }.flowOn(Dispatchers.IO)
}