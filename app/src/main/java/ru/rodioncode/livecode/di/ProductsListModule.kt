package ru.rodioncode.livecode.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import ru.rodioncode.livecode.data.ProductsRepository
import ru.rodioncode.livecode.domain.GetProductByIdUseCase
import ru.rodioncode.livecode.domain.GetProductByIdUseCaseImpl
import ru.rodioncode.livecode.domain.GetProductsUseCase
import ru.rodioncode.livecode.domain.GetProductsUseCaseImpl
import ru.rodioncode.livecode.domain.ProductsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsListModule {
    @Binds
    abstract fun bindsGetProductsUseCase(getProductsUseCase: GetProductsUseCaseImpl): GetProductsUseCase

    @Binds
    abstract fun bindsGetProductByIdUseCase(getProductsUseCase: GetProductByIdUseCaseImpl): GetProductByIdUseCase

    @Binds
    abstract fun bindsProductsRepository(productsRepository: ProductsRepositoryImpl): ProductsRepository
}