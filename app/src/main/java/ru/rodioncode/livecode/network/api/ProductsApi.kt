package ru.rodioncode.livecode.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.rodioncode.livecode.data.ProductDto

interface ProductsApi {
    // Получить список продуктов
    @GET("/products")
    suspend fun getProducts(): List<ProductDto>

    // Get a single product by id
    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}