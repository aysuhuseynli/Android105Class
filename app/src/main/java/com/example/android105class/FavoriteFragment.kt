package com.example.android105class

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android105class.adapters.ProductsAdapter
import com.example.android105class.dataClass.ProductDTO
import com.example.android105class.database.RoomDB
import com.example.android105class.databinding.FragmentFavoriteBinding
import com.example.android105class.di.MyApplication
import com.example.android105class.viewModel.ProductsViewModel
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: ProductsAdapter
    @Inject
    lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAllFavorites()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    private fun setAdapter(favProducts:MutableList<ProductDTO>) {
        adapter=ProductsAdapter({},{})
        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter=adapter
    }

    private fun observeAllFavorites(){
        viewModel.getAllFavorites().observe(viewLifecycleOwner){ favList->
            val mappedList=favList.map {
                ProductDTO(
                    it.id,
                    it.category,
                    it.description,
                    it.price,
                    it.image,
                    it.title,
                    favStatus = true
                )
            }
            setAdapter(mappedList.toMutableList())
            adapter.submitList(mappedList.toMutableList())
        }
    }
}