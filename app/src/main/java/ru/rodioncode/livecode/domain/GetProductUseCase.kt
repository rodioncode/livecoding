package ru.rodioncode.livecode.domain

import ru.rodioncode.livecode.data.ProductDto

interface GetProductsUseCase {
    suspend operator fun invoke(): List<ProductDto>
}

