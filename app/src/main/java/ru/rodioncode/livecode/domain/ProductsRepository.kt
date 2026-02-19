package ru.rodioncode.livecode.domain

import kotlinx.coroutines.flow.Flow
import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.data.ProductsRepository
import ru.rodioncode.livecode.network.NetworkResult
import ru.rodioncode.livecode.network.api.ProductsApi
import ru.rodioncode.livecode.network.safeCall
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: ProductsApi
): ProductsRepository {
    override suspend fun getProducts(): NetworkResult<List<ProductDto>> {
        return safeCall {
            api.getProducts()
        }
    }

    override suspend fun getProductById(id: Int): NetworkResult<ProductDto> {
        return safeCall {
            api.getProductById(id)
        }
    }
}