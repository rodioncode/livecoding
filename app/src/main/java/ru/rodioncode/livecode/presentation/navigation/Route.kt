package ru.rodioncode.livecode.presentation.navigation

sealed class Route(val route: String) {
    object Main : Route("products_list")

    object Details : Route("product_details/{productId}"){
        const val DETAILS_ARGUMENT_ID = "productId"
        fun createRoute(productId: Int) = "product_details/${productId}"
    }
}