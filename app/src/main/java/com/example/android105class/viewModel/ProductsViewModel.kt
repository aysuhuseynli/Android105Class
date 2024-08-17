package com.example.android105class.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android105class.api.ProductService
import com.example.android105class.dataClass.ProductDTO
import com.example.android105class.database.FavProductDao
import com.example.android105class.database.FavProductEntity
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val favProductDao: FavProductDao,
    private val productService: ProductService
):ViewModel() {
    private val _productList=MutableLiveData<MutableList<ProductDTO>>()
    val productList :LiveData<MutableList<ProductDTO>> get() = _productList

    fun getAllProducts(){

        CoroutineScope(Dispatchers.IO).launch{
            val response=productService.getAllProducts()
            if(response.isSuccessful){
                val products=response.body()?.products
                val productListDto=products?.map{
                    val isFavorite=favProductDao.getProductsById(it.id)!=null
                    ProductDTO(
                        it.id,
                        it.category,
                        it.description,
                        it.price,
                        it.thumbnail,
                        it.title,
                        isFavorite
                    )
                }
                _productList.postValue(productListDto!!.toMutableList())
            }
        }
    }

    fun addFavList(product: ProductDTO){
        viewModelScope.launch(Dispatchers.IO) {
            val favProductEntity=FavProductEntity(
                id = product.id,
                title = product.title,
                price = product.price,
                description = product.description,
                category = product.category,
                image = product.thumbnail,
                favStatus = true
            )
            favProductDao.insert(listOf(favProductEntity))
        }
    }

    fun removeFavList(product: ProductDTO){
        viewModelScope.launch(Dispatchers.IO) {
            val favProduct=favProductDao.getProductsById(product.id)
            if(favProduct!=null){
                favProductDao.delete(favProduct)
            }
        }
    }

    fun getAllFavorites()=favProductDao.getAllFavorites()
}