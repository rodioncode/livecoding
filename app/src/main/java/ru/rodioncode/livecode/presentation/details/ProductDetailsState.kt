package ru.rodioncode.livecode.presentation.details

import ru.rodioncode.livecode.presentation.productsList.ProductUi

sealed interface ProductDetailsState {
    object Loading : ProductDetailsState
    data class Success(val products: ProductUi) : ProductDetailsState
    data class Error(val message: String) : ProductDetailsState
}