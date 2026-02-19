package ru.rodioncode.livecode.presentation.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.BindsInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.domain.GetProductByIdUseCase
import ru.rodioncode.livecode.domain.GetProductsUseCase
import ru.rodioncode.livecode.network.NetworkResult
import ru.rodioncode.livecode.presentation.navigation.Route.Details.DETAILS_ARGUMENT_ID
import ru.rodioncode.livecode.presentation.productsList.ProductUi
import ru.rodioncode.livecode.presentation.productsList.ProductsListAction
import ru.rodioncode.livecode.presentation.productsList.ProductsListState
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductById: GetProductByIdUseCase,
) : ViewModel() {

    val id = checkNotNull(savedStateHandle.get<Int>(DETAILS_ARGUMENT_ID))

    val _state = MutableStateFlow<ProductDetailsState>(ProductDetailsState.Loading)

    val state: StateFlow<ProductDetailsState> = _state

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    fun reduce(action: ProductsListAction) = when (action) {
        is ProductsListAction.OnProductClicked -> {
            // navigate to product details by id
        }
        is ProductsListAction.Retry -> {
            viewModelScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        delay(1000)
        val product = getProductById(id)
        when (product) {
            is NetworkResult.Error -> {
                Log.e(TAG, "Error: ${product.message}")
                _state.emit(ProductDetailsState.Error(product.message.toString()))
            }

            is NetworkResult.Success<ProductUi> -> {
                _state.emit(ProductDetailsState.Success(products = product.data))
            }
        }
    }

    companion object {
        val TAG = "ProductDetailsViewModel"
    }
}