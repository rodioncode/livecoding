package ru.rodioncode.livecode.presentation.productsList

sealed interface ProductsListState {
    object Loading : ProductsListState
    data class Success(val products: List<ProductUi>) : ProductsListState
    data class Error(val message: String) : ProductsListState
}