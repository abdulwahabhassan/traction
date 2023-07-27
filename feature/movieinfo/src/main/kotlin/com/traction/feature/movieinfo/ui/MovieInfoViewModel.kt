package com.traction.feature.movieinfo.ui

import androidx.lifecycle.viewModelScope
import com.traction.core.common.model.State
import com.traction.core.common.model.onFailure
import com.traction.core.common.model.onLoading
import com.traction.core.common.model.onReady
import com.traction.core.common.viewmodel.BaseViewModel
import com.traction.core.domain.usecase.GetMovieInfoUseCase
import com.traction.core.model.MovieInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val movieInfoUseCase: GetMovieInfoUseCase
) : BaseViewModel<MovieInfoScreenEvent, MovieInfoState>(MovieInfoState()) {

    override suspend fun handleUiEvents(event: MovieInfoScreenEvent) {
        when (event) {
            is MovieInfoScreenEvent.OnGetMovieInfo -> {
                getMovieInfo(event.movieId)
            }
        }
    }

    private suspend fun getMovieInfo(id: Long) {
        movieInfoUseCase.invoke(id).onEach { resource ->
            resource.onLoading {
                setUiState { copy(state = State.Loading) }
            }
            resource.onReady { movieInfo: MovieInfo ->
                setUiState { copy(state = State.Success(data = movieInfo)) }
            }
            resource.onFailure { failureMessage ->
                setUiState { copy(state = State.Error(message = failureMessage)) }
            }
        }.catch {
            it.printStackTrace()
            setUiState {
                copy(
                    state = State.Error(
                        message = it.localizedMessage ?: it.message
                        ?: "An unexpected event occurred!"
                    )
                )
            }
        }.launchIn(viewModelScope)
    }
}