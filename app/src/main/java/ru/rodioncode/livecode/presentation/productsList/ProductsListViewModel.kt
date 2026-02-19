package ru.rodioncode.livecode.presentation.productsList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rodioncode.livecode.data.ProductDto
import ru.rodioncode.livecode.domain.GetProductsUseCase
import ru.rodioncode.livecode.network.NetworkResult
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
) : ViewModel() {

    val _state = MutableStateFlow<ProductsListState>(ProductsListState.Loading)

    val state: StateFlow<ProductsListState> = _state

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
        val products = getProducts()
        when (products) {
            is NetworkResult.Error -> {
                Log.e(TAG, "Error: ${products.message}")
                _state.emit(ProductsListState.Error(products.message.toString()))
            }

            is NetworkResult.Success<List<ProductUi>> -> {
                _state.emit(ProductsListState.Success(products = products.data))
            }
        }
    }

    companion object {
        val TAG = "ProductsListViewModel"
    }
}