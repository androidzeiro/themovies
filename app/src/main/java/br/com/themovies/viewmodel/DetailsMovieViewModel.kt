package br.com.themovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.themovies.model.StatusDTO
import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<DetailsMovieResponse>()
    val movie: LiveData<DetailsMovieResponse> get() = _movie

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getDetailsMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getDetailsMovie(movieId).collect { response ->
                when (response.statusDTO) {
                    StatusDTO.SUCCESS -> {
                        _loading.postValue(false)
                        response.data?.let {
                            _movie.postValue(it)
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