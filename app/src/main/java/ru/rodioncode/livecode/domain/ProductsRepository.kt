package ru.rodioncode.livecode.domain

import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.data.ProductsRepository
import ru.rodioncode.livecode.network.api.ProductsApi
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: ProductsApi
): ProductsRepository {
    override suspend fun getProducts(): List<ProductDto> {
        return api.getProducts()
    }
}