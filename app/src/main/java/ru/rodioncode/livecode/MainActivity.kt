package ru.rodioncode.livecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.rodioncode.livecode.presentation.ProductsListScreen
import ru.rodioncode.livecode.presentation.ProductsListViewModel
import ru.rodioncode.livecode.ui.theme.LiveCodeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val productsListViewModel by viewModels<ProductsListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveCodeTheme {
                ProductsListScreen(productsListViewModel)
            }
        }
    }
}
