package ru.rodioncode.livecode.data

import ru.rodioncode.livecode.network.NetworkResult

interface ProductsRepository {
    suspend fun getProducts(): NetworkResult<List<ProductDto>>

    suspend fun getProductById(id: Int): NetworkResult<ProductDto>
}