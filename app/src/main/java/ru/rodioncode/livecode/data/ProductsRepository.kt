package ru.rodioncode.livecode.data

interface ProductsRepository {
    suspend fun getProducts(): List<ProductDto>
}