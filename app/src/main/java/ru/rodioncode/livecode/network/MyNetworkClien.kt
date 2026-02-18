package ru.rodioncode.livecode.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rodioncode.livecode.network.api.ProductsApi


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @Provides
    fun productsApi(retrofit: Retrofit) = retrofit.create(ProductsApi::class.java)
}