package com.traction.feature.movieinfo.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.traction.core.common.Extension.capitalizeEachWord
import com.traction.core.common.Extension.capitalizeFirstLetter
import com.traction.core.common.Util
import com.traction.core.common.model.State
import com.traction.core.designsystem.theme.TractionTheme
import com.traction.core.designsystem.theme.black
import com.traction.core.designsystem.theme.green
import com.traction.core.designsystem.theme.grey
import com.traction.core.designsystem.theme.lightGrey
import com.traction.core.designsystem.theme.pink
import com.traction.core.designsystem.theme.primaryBlue
import com.traction.core.designsystem.theme.red
import com.traction.core.designsystem.theme.transparentAsh
import com.traction.core.designsystem.theme.transparentGreen
import com.traction.core.designsystem.theme.transparentPink
import com.traction.core.designsystem.theme.transparentTurquoise
import com.traction.core.designsystem.theme.transparentYellow
import com.traction.core.designsystem.theme.yellow
import com.traction.core.designsystem.ui.ErrorScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
internal fun MovieInfoRoute(
    viewModel: MovieInfoViewModel = hiltViewModel(),
    onBackPress: () -> Unit,
    movieId: Long,
) {
    BackHandler { onBackPress() }
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    MovieInfoScreen(
        screenState = screenState,
        movieId = movieId
    ) { uiEvent: MovieInfoScreenEvent -> viewModel.sendEvent(uiEvent) }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieInfoScreen(
    screenState: MovieInfoState,
    movieId: Long,
    onUiEvent: (MovieInfoScreenEvent) -> Unit
) {
    if (screenState.state is State.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(strokeWidth = 2.dp)
        }
    } else {
        if (screenState.state is State.Success) {
            Scaffold { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    item {
                        Image(
                            painter = rememberAsyncImagePainter(model = screenState.state.data.backdropPath),
                            contentDescription = "Poster",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = lightGrey)
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = screenState.state.data.originalTitle,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .padding(end = 4.dp, start = 4.dp),
                                        imageVector = Icons.Rounded.Favorite,
                                        contentDescription = "Popularity",
                                        tint = pink
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = String.format(
                                            "%.1f",
                                            screenState.state.data.voteAverage
                                        ),
                                        color = black,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                ) {

                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_date),
                                            "Date",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .padding(end = 4.dp),
                                            tint = primaryBlue
                                        )

                                        Text(
                                            modifier = Modifier,
                                            text = LocalDate
                                                .parse(screenState.state.data.releaseDate)
                                                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                                                ?: "",
                                            color = grey,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }


                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = "|",
                                        color = grey,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        modifier = Modifier,
                                        text = screenState.state.data.originalLanguage.capitalizeFirstLetter()
                                            ?: "",
                                        color = grey,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = when (val votes =
                                        screenState.state.data.voteCount ?: 0) {
                                        in 1000..999_999 -> votes.toString().dropLast(3) + "k"
                                        in 1_000_000..999_999_999 -> votes.toString()
                                            .dropLast(3) + "m"

                                        in 1_000_000_000..999_999_999_999 -> votes.toString()
                                            .dropLast(3) + "b"

                                        else -> votes.toString()
                                    } + " votes",
                                    color = green,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .background(
                                            transparentGreen,
                                            RoundedCornerShape(4.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )

                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                item {
                                    Icon(
                                        painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_genre),
                                        contentDescription = "Genres",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 4.dp),
                                        tint = primaryBlue
                                    )
                                }

                                items(screenState.state.data.genres.map { it.name }) { item ->
                                    val itemBgColor = listOf(
                                        transparentTurquoise,
                                        transparentGreen,
                                        transparentPink,
                                        transparentYellow,
                                        transparentAsh,
                                        transparentTurquoise
                                    ).random()
                                    Text(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .background(
                                                color = itemBgColor,
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(horizontal = 12.dp, vertical = 4.dp),
                                        text = item.capitalizeEachWord(),
                                        color = when (itemBgColor) {
                                            transparentGreen -> green
                                            transparentPink -> red
                                            transparentYellow -> yellow
                                            transparentAsh -> black
                                            transparentTurquoise -> primaryBlue
                                            else -> grey
                                        },
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_runtime),
                                    "Runtime",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 4.dp),
                                    tint = primaryBlue
                                )
                                Text(
                                    text = (screenState.state.data.runtime.toString()
                                        .plus(" minutes")),
                                    color = grey,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_description),
                                    "Overview",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 4.dp),
                                    tint = primaryBlue
                                )
                                Text(
                                    text = screenState.state.data.overview ?: "",
                                    color = grey,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_budget),
                                    "Budget",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 4.dp),
                                    tint = primaryBlue
                                )

                                Text(
                                    modifier = Modifier.padding(end = 16.dp),
                                    text = "Budget: $" + screenState.state.data.budget?.let { budget ->
                                        Util.formatAmount(budget)
                                    },
                                    color = grey,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_revenue),
                                    "Revenue",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 4.dp),
                                    tint = primaryBlue
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Revenue: $" + screenState.state.data.revenue.let { revenue ->
                                        Util.formatAmount(revenue)
                                    },
                                    color = grey,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }

            }
        } else if (screenState.state is State.Error) {
            ErrorScreen(
                message = screenState.state.message,
                onRetryClick = { onUiEvent(MovieInfoScreenEvent.OnGetMovieInfo(movieId)) }
            )
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        onUiEvent(MovieInfoScreenEvent.OnGetMovieInfo(movieId))
    })
}


@Composable
@Preview(showBackground = true)
fun MovieInfoScreenPreview() {
    TractionTheme {
        MovieInfoScreen(
            screenState = MovieInfoState(),
            movieId = 1
        ) {}
    }
}
