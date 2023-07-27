package com.traction.feature.movielist.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.traction.core.designsystem.theme.primaryBlue
import com.traction.core.designsystem.theme.transparentTurquoise
import com.traction.core.designsystem.theme.TractionTheme

@Composable
fun Header(category: String, onFilterClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = transparentTurquoise,
            )
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = category,
            color = primaryBlue,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable {
                    onFilterClick()
                }
                .padding(6.dp),
            painter = painterResource(id = com.traction.core.designsystem.R.drawable.ic_filter),
            contentDescription = "Filter",
            tint = primaryBlue
        )
    }
}


@Composable
@Preview(showBackground = true)
fun HeaderPreview() {
    TractionTheme {
        Header("Popular", onFilterClick = {})
    }
}