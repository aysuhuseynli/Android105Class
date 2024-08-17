package com.example.android105class.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android105class.R
import com.example.android105class.adapters.ProductsAdapter
import com.example.android105class.dataClass.ProductDTO
import com.example.android105class.database.RoomDB
import com.example.android105class.databinding.FragmentProductsBinding
import com.example.android105class.di.MyApplication
import com.example.android105class.viewModel.ProductsViewModel
//import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding:FragmentProductsBinding
    private lateinit var adapter: ProductsAdapter
    @Inject lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getAllProducts()
        }
        observeProducts()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    private fun observeProducts(){
        viewModel.productList.observe(viewLifecycleOwner){
            setAdapter(it)
        }
    }

    private fun setAdapter(productsList:MutableList<ProductDTO>){
        adapter= ProductsAdapter(
            {deleteItem(it, productsList)},
            {addFavList(it,productsList)}
        )

        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        adapter.submitList(productsList)
    }

    private fun deleteItem(position:Int, products:MutableList<ProductDTO>){
        products.removeAt(position)
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }

    private fun addFavList(position: Int, products:MutableList<ProductDTO>){
        val product=products[position]
        if(product.favStatus){
            viewModel.addFavList(product)
        }else{
            viewModel.removeFavList(product)
        }
    }
}