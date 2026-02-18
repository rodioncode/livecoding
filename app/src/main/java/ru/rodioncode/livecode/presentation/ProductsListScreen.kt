package ru.rodioncode.livecode.presentation

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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.rodioncode.livecode.data.ProductDto

@Composable
fun ProductsListScreen(productsListViewModel: ProductsListViewModel) {
    /*val items = remember {
        mutableStateListOf<ProductDto>(
            ProductDto(
                category = "qualisque",
                description = "vituperatoribus",
                id = 8991,
                image = "https://cataas.com/cat",
                price = 2.3,
                rating = Rating(count = 7279, rate = 6.7),
                title = "definitiones"
            ),
            ProductDto(
                category = "qualisque",
                description = "vituperatoribus",
                id = 8991,
                image = "https://cataas.com/cat",
                price = 2.3,
                rating = Rating(count = 7279, rate = 6.7),
                title = "definitiones"
            ),
            ProductDto(
                category = "qualisque",
                description = "vituperatoribus",
                id = 8991,
                image = "https://cataas.com/cat",
                price = 2.3,
                rating = Rating(count = 7279, rate = 6.7),
                title = "definitiones"
            ),
        )
    }*/

    val state = productsListViewModel.state.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Магазин товаров",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            if(state.value is ProductsListState.Success) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items((state.value as ProductsListState.Success).products) {
                        ItemCard(it)
                    }
                }
            }
        }

    }
}

@Composable
private fun ItemCard(dto: ProductDto) {
    Card {
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
                Row(){
                    Text(text = "Rating: " + dto.rating.rate, style = MaterialTheme.typography.bodyMedium, )
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "Localized description",
                    )
                }
            }
        }

    }
}
