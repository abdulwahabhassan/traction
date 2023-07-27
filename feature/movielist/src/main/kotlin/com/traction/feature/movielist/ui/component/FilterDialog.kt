package com.traction.feature.movielist.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.traction.core.common.Extension.capitalizeEachWord
import com.traction.core.designsystem.theme.black
import com.traction.core.designsystem.theme.primaryBlue
import com.traction.core.designsystem.theme.transparentTurquoise
import com.traction.core.designsystem.theme.TractionTheme
import com.traction.core.model.Category
import com.traction.feature.movielist.R
import com.traction.feature.movielist.ui.MovieListScreenEvent

@Composable
fun FilterDialog(
    onDismissDialog: () -> Unit,
    onUiEvent: (MovieListScreenEvent) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissDialog,
    ) {
        LazyColumn(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp, vertical = 24.dp)

        ) {
            item {
                Text(
                    text = stringResource(R.string.title_see_what_s_trending),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                    color = black,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            items(
                listOf(
                    Category.NOW_PLAYING,
                    Category.POPULAR,
                    Category.TOP_RATED
                )
            ) { category ->
                Text(
                    text = category.name.capitalizeEachWord(),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth()
                        .background(
                            color = transparentTurquoise,
                            shape = RoundedCornerShape(50)
                        )
                        .clip(RoundedCornerShape(50))
                        .clickable {
                            onUiEvent(MovieListScreenEvent.OnFilterSelected(category))
                            onDismissDialog()
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    color = primaryBlue,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun FilterDialogPreview() {
    TractionTheme {
        FilterDialog(onDismissDialog = { }, onUiEvent = {})
    }
}