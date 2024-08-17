package com.example.android105class.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android105class.R
import com.example.android105class.dataClass.Product
import com.example.android105class.dataClass.ProductDTO
import com.example.android105class.databinding.ProductViewBinding

class ProductsAdapter(
    private val deleteItem:(Int)->Unit,
    private val addFavList:(Int)->Unit
    ): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val diffCallBack= object :DiffUtil.ItemCallback<ProductDTO>(){
        override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem==newItem
        }
    }

    private val diffUtil=AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding=ProductViewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val user=diffUtil.currentList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    inner class ProductsViewHolder(private val binding: ProductViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductDTO){
            binding.price.text="${product.price}$"
            binding.title.text=product.title
            binding.favBtn.setImageResource(if (product.favStatus) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
            Glide.with(binding.root)
                .load(product.thumbnail)
                .into(binding.imageView)
            binding.deleteBtn.setOnClickListener {
                deleteItem(adapterPosition)
            }
            binding.favBtn.setOnClickListener {
                product.favStatus=!product.favStatus
                addFavList(adapterPosition)
                updateFavStatus(product.favStatus)
            }
        }

        private fun updateFavStatus(isFavorite:Boolean){
            if (isFavorite){
                binding.favBtn.setImageResource(R.drawable.baseline_favorite_24)
            }else{
                binding.favBtn.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }
    }

    fun submitList(list: MutableList<ProductDTO>){
        diffUtil.submitList(list)
    }
}