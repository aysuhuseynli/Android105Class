package com.example.android105class

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.android105class.adapters.PageAdapter
import com.example.android105class.databinding.FragmentHomeBinding
import com.example.android105class.di.MyApplication

//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    fun setAdapter() {
        val viewPager = binding.viewPager
        val adapter = PageAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = when (menuItem.itemId) {
                R.id.products -> 0
                R.id.favorites -> 1
                else -> 0
            }
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigationMenu.selectedItemId = when (position) {
                    0 -> R.id.products
                    1 -> R.id.favorites
                    else -> R.id.products
                }
            }
        })

    }
}