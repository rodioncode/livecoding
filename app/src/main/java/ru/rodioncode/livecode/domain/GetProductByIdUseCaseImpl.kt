package ru.rodioncode.livecode.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.rodioncode.livecode.data.ProductsRepository
import ru.rodioncode.livecode.network.NetworkResult
import ru.rodioncode.livecode.network.map
import ru.rodioncode.livecode.presentation.productsList.ProductUi
import javax.inject.Inject

class GetProductByIdUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository,
    private val mapper: ProductUiMapper,
) : GetProductByIdUseCase {

    override suspend fun invoke(id: Int): NetworkResult<ProductUi> = withContext(Dispatchers.IO) {
        return@withContext repository.getProductById(id)
            .map {
                mapper.toProductUi(it)
            }
    }
}
