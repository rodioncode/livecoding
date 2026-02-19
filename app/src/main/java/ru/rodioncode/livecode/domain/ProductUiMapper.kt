package ru.rodioncode.livecode.domain

import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.presentation.productsList.ProductUi
import javax.inject.Inject

class ProductUiMapper @Inject constructor() {

    fun toProductUi(productDto: ProductDto) = ProductUi(
        id = productDto.id,
        title = productDto.title,
        image = productDto.image,
        price = productDto.price,
        ratingRate = productDto.rating.rate.toString()
    )

    fun transform(productDtoList: List<ProductDto>) : List<ProductUi> = productDtoList.map {
        it.toProductUi()
    }
}

fun ProductDto.toProductUi() = ProductUi(
    id = this.id,
    title = this.title,
    image = this.image,
    price = this.price,
    ratingRate = this.rating.rate.toString()
)
