package ru.rodioncode.livecode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rodioncode.livecode.domain.GetProductsUseCase
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
) : ViewModel() {

    val _state = MutableStateFlow<ProductsListState>(ProductsListState.Loading)

    val state: StateFlow<ProductsListState> = _state

    init {
        viewModelScope.launch {
            val products = getProducts()
            _state.emit(ProductsListState.Success(products = products))

            //_state.emit(ProductsListState.Error(e.message.toString()))
        }
    }

    fun reduce(action: ProductsListAction) = when (action) {
        is ProductsListAction.onProductClicked -> {}
        else -> {}
    }
}