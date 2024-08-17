package com.example.android105class.fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android105class.R
import com.example.android105class.di.MyApplication

//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
    }


}