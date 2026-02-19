package ru.rodioncode.livecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.rodioncode.livecode.presentation.details.ProductDetailsViewModel
import ru.rodioncode.livecode.presentation.details.ProductsDetailScreen
import ru.rodioncode.livecode.presentation.navigation.Route
import ru.rodioncode.livecode.presentation.navigation.Route.Details.DETAILS_ARGUMENT_ID
import ru.rodioncode.livecode.presentation.productsList.ProductsListScreen
import ru.rodioncode.livecode.presentation.productsList.ProductsListViewModel
import ru.rodioncode.livecode.ui.theme.LiveCodeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveCodeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.Main.route
                ) {
                    composable(Route.Main.route) {
                        val productsListViewModel: ProductsListViewModel = hiltViewModel()

                        ProductsListScreen(
                            productsListViewModel.state,
                            productsListViewModel::reduce,
                            navController,
                        )
                    }
                    composable(
                        route = Route.Details.route,
                        arguments = listOf(
                            navArgument(DETAILS_ARGUMENT_ID) {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()

                        ProductsDetailScreen(
                            productDetailsViewModel.state,
                            // navController,
                            // reducer,
                        )
                    }
                }
            }
        }
    }
}



