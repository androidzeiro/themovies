package br.com.themovies.di

import br.com.themovies.repository.MoviesRepository
import br.com.themovies.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository
}