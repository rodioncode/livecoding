package ru.rodioncode.livecode.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.data.ProductsRepository
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(private val repository: ProductsRepository): GetProductsUseCase {

    override suspend fun invoke(): List<ProductDto> = withContext(Dispatchers.IO) {
        return@withContext repository.getProducts()
    }
}