package com.challenge.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.challenge.designsystem.component.NodeCard
import com.challenge.designsystem.screen.ErrorScreen
import com.challenge.designsystem.screen.LoadingScreen
import com.challenge.home.R
import com.challenge.home.ui.viewmodel.BipaEvent
import com.challenge.home.ui.viewmodel.BipaState
import com.challenge.home.ui.viewmodel.BipaViewModel
import com.challenge.model.City
import com.challenge.model.Country
import com.challenge.model.Node
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier, viewModel: BipaViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> LoadingScreen(modifier = modifier)
        uiState.error != null -> ErrorScreen(text = uiState.error)
        else -> HomeContent(uiState = uiState)
    }

    LaunchedEffect(Unit) {
        viewModel.sendEvent(event = BipaEvent.LoadNodes)
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: BipaState = BipaState()
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.mobile_coding_challenge),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        items(uiState.nodes) { node ->
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NodeCard(
                    capacity = node.capacity,
                    firstSeen = node.firstSeen,
                    updatedAt = node.updatedAt,
                    city = node.city?.ptBR,
                    country = node.country?.ptBR
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeContent(
        uiState = BipaState(
            nodes = listOf(
                Node(
                    capacity = "123456789",
                    firstSeen = "2023-09-01",
                    updatedAt = "12:00:00",
                    city = City(
                        ptBR = "São Paulo"
                    ),
                    country = null
                ),
                Node(
                    capacity = "123456789",
                    firstSeen = "2023-09-01",
                    updatedAt = "12:00:00",
                    city = City(
                        ptBR = "São Paulo"
                    ),
                    country = Country(
                        ptBR = "Brasil"
                    )
                ),
                Node(
                    capacity = "123456789",
                    firstSeen = "2023-09-01",
                    updatedAt = "12:00:00",
                    city = null,
                    country = Country(
                        ptBR = "Brasil"
                    )
                ),
                Node(
                    capacity = "123456789",
                    firstSeen = "2023-09-01",
                    updatedAt = "12:00:00",
                    city = null,
                    country = null
                )
            )
        )
    )
}