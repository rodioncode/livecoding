package ru.rodioncode.livecode.presentation

import ru.rodioncode.livecode.data.ProductDto

sealed interface ProductsListState {
    object Loading : ProductsListState
    data class Success(val products: List<ProductDto>) : ProductsListState
    data class Error(val message: String) : ProductsListState
}