package com.traction.feature.movielist.ui

import androidx.lifecycle.viewModelScope
import com.traction.core.common.model.Resource
import com.traction.core.common.model.State
import com.traction.core.common.viewmodel.BaseViewModel
import com.traction.core.data.datastore.UserPreferencesDataStore
import com.traction.core.domain.usecase.GetMovieListUseCase
import com.traction.core.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMovieListUseCase,
    private val userPreferencesDataStore: UserPreferencesDataStore
) : BaseViewModel<MovieListScreenEvent, MovieListsScreenState>(MovieListsScreenState()) {

    override suspend fun handleUiEvents(event: MovieListScreenEvent) {
        when (event) {
            is MovieListScreenEvent.OnFilterSelected -> {
                userPreferencesDataStore.update {
                    copy(event.category)
                }
                getMovieMoveList(event.category)
            }

            MovieListScreenEvent.OnRetry -> {
                val prefData = userPreferencesDataStore.data()
                getMovieMoveList(prefData.movieCategoryFilter)
            }
        }
    }

    init {
        viewModelScope.launch {
            val prefData = userPreferencesDataStore.data()
            getMovieMoveList(prefData.movieCategoryFilter)
        }
    }

    private suspend fun getMovieMoveList(category: Category) {
        when (val resource =
            movieListUseCase.invoke(category.name.lowercase(Locale.getDefault()))) {
            is Resource.Loading -> {
                setUiState {
                    copy(
                        state = State.Loading,
                        category = category.name.lowercase(Locale.getDefault())
                    )
                }
            }

            is Resource.Ready -> {
                setUiState {
                    copy(
                        state = State.Success(data = resource.data),
                        category = category.name.lowercase(Locale.getDefault())
                    )
                }
            }

            is Resource.Failed -> {
                setUiState {
                    copy(
                        state = State.Error(message = resource.message),
                        category = category.name.lowercase(Locale.getDefault())
                    )
                }
            }
        }
    }
}