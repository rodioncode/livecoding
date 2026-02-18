package ru.rodioncode.livecode.network.api

import retrofit2.http.GET
import ru.rodioncode.livecode.data.ProductDto

interface ProductsApi {
    @GET("/products")
    suspend fun getProducts(): List<ProductDto>
}