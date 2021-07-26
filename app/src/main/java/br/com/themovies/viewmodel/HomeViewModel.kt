package br.com.themovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.themovies.model.StatusDTO
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import br.com.themovies.model.response.upcomingmovies.UpComingMoviesResponse
import br.com.themovies.repository.MoviesRepository
import kotlinx.coroutines.flow.collect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import java.util.stream.IntStream
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private var _nextPage: Int = 1

    private val movieList = mutableSetOf<ResultResponse>()
    private val _movies = MutableLiveData<Set<ResultResponse>>()
    val movies: LiveData<Set<ResultResponse>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUpCommingMovies() {
        viewModelScope.launch {
            repository.getUpCommingMovies(_nextPage).collect { response ->
                when (response.statusDTO) {
                    StatusDTO.SUCCESS -> {
                        _loading.postValue(false)
                        response.data?.let {
                            movieList.addAll(it.results)
                            _movies.value = movieList
                            if (it.totalPages != _nextPage) _nextPage++
                        }
                    }
                    StatusDTO.LOADING -> {
                        _loading.postValue(true)
                    }
                    StatusDTO.ERROR -> {
                        _loading.postValue(false)
                        _error.postValue(response.message ?: "")
                    }
                }
            }
        }
    }
}