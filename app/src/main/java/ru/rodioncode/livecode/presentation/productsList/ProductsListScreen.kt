package ru.rodioncode.livecode.presentation.productsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow
import ru.rodioncode.livecode.R
import ru.rodioncode.livecode.presentation.navigation.Route

@Composable
fun ProductsListScreen(
    vmState: StateFlow<ProductsListState>,
    onAction: (ProductsListAction) -> Unit,
    navController: NavHostController,
) {
    val state = vmState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = stringResource(R.string.products_list_screen_title),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineLarge
            )

            when (state.value) {
                is ProductsListState.Error -> ProductsListErrorState(
                    error = (state.value as ProductsListState.Error).message,
                    onRetry = { onAction(ProductsListAction.Retry) }
                )

                ProductsListState.Loading -> ProductsListLoadingState()
                is ProductsListState.Success -> ProductsListSuccessState(
                    state.value
                ) { id -> navController.navigate(Route.Details.createRoute(id.toInt())) }
            }
        }

    }
}

@Composable
fun ProductsListLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Loading...")
        CircularProgressIndicator()
    }
}

@Composable
fun ProductsListErrorState(error: String, onRetry: () -> Unit = { }) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Error: $error")
        Button(
            onClick = onRetry,
        ) { Text("Retry") }
    }
}

@Composable
private fun ProductsListSuccessState(
    value: ProductsListState,
    navigateToProduct: (String) -> Unit = { },
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items((value as ProductsListState.Success).products) {
            ItemCard(it, navigateToProduct)
        }
    }
}

@Composable
private fun ItemCard(dto: ProductUi, navigateToProduct: (String) -> Unit = { }) {
    Card(onClick = { navigateToProduct(dto.id.toString()) }) {
        Row(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = dto.image,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .padding()
                    .size(100.dp),
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = dto.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Price: " + dto.price, style = MaterialTheme.typography.bodyMedium)
                Row {
                    Text(
                        text = "Rating: " + dto.ratingRate,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Localized description",
                    )
                }
            }
        }

    }
}
