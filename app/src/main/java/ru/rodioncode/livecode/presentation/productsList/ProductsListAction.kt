package ru.rodioncode.livecode.presentation.productsList

sealed interface ProductsListAction {
    data class OnProductClicked(val productId: String) : ProductsListAction
    object Retry : ProductsListAction
}