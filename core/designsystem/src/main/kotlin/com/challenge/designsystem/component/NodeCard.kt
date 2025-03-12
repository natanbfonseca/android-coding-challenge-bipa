package com.challenge.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.designsystem.R

@Composable
fun NodeCard(
    modifier: Modifier = Modifier,
    capacity: String?,
    firstSeen: String?,
    updatedAt: String?,
    city: String?,
    country: String?
) {
    val formattedLocation = when {
        !city.isNullOrBlank() && !country.isNullOrBlank() -> "$city - $country"
        !city.isNullOrBlank() -> city
        !country.isNullOrBlank() -> country
        else -> null
    }

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.btc, capacity.orEmpty()),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            InfoRow(label = stringResource(R.string.first_seen), value = firstSeen.orEmpty())

            InfoRow(label = stringResource(R.string.updated_at), value = updatedAt.orEmpty())

            formattedLocation?.let { location ->
                InfoRow(
                    label = stringResource(R.string.location),
                    value = location
                )
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            color = Color.Gray
        )
        Text(
            text = value,
            modifier = Modifier.weight(1.5f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun NodeCardPreview() {
    NodeCard(
        capacity = "123456789",
        firstSeen = "2023-09-01",
        updatedAt = "12:00:00",
        city = "São Paulo",
        country = "Brasil"
    )
}