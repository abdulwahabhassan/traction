package com.traction.feature.movielist.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.traction.core.designsystem.theme.grey
import com.traction.core.designsystem.theme.lightGrey
import com.traction.core.designsystem.theme.pink
import com.traction.core.designsystem.theme.transparentTurquoise
import com.traction.core.model.Movie
import com.traction.feature.movielist.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
fun LazyGridScope.movieListGrid(
    movies: LazyPagingItems<Movie>,
    onNavigateToMovieInfo: (Long) -> Unit
) {
    items(movies.itemCount) { movieIndex ->
        movies[movieIndex]?.let { movie ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                onClick = {
                    movies[movieIndex]?.id?.let { onNavigateToMovieInfo(it) }
                },
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.background(transparentTurquoise)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.posterPath),
                        contentDescription = "Poster",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .background(color = lightGrey)
                            .height(200.dp),
                        contentScale = ContentScale.Crop

                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = movie.originalTitle,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = movie.overview,
                            color = grey,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = LocalDate.parse(movie.releaseDate)
                                    .format(
                                        DateTimeFormatter.ofPattern(
                                            stringResource(R.string.patter_mmm_dd_yyyy)
                                        )
                                    )
                                    ?: "",
                                color = grey,
                                style = MaterialTheme.typography.labelMedium
                            )
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(0.3f)
                                    .padding(end = 4.dp, start = 4.dp),
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = stringResource(R.string.msg_popularity),
                                tint = pink
                            )

                            Text(
                                text = String.format("%.1f", movie.voteAverage),
                                color = grey,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
