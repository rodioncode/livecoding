package ru.rodioncode.livecode.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow
import ru.rodioncode.livecode.presentation.productsList.ProductUi

@Composable
fun ProductsDetailScreen(vmState: StateFlow<ProductDetailsState>) {

    val state = vmState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Магазин товаров",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineLarge
            )

            when (state.value) {
                is ProductDetailsState.Error -> {
                    Text(text = (state.value as ProductDetailsState.Error).message)
                }
                ProductDetailsState.Loading -> {

                    Text(text = "Loading")
                }
                is ProductDetailsState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val successState = state.value as ProductDetailsState.Success
                        ItemCard(
                            // From state from viewModel
                            ProductUi(
                                id = successState.products.id,
                                title = successState.products.title,
                                image = successState.products.image,
                                price = successState.products.price,
                                ratingRate = successState.products.ratingRate
                            )
                        )
                    }
                }
            }


        }

    }
}

@Composable
private fun ItemCard(model: ProductUi) {
    Card {
        Row(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = model.image,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .padding()
                    .size(100.dp),
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = model.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Price: " + model.price, style = MaterialTheme.typography.bodyMedium)
                Row() {
                    Text(
                        text = "Rating: " + model.ratingRate,
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
