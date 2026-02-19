package ru.rodioncode.livecode.domain

import ru.rodioncode.livecode.network.NetworkResult
import ru.rodioncode.livecode.presentation.productsList.ProductUi

interface GetProductByIdUseCase {
    suspend operator fun invoke(id: Int): NetworkResult<ProductUi>
}