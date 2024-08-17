package com.example.android105class.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android105class.R
import com.example.android105class.api.LoginService
import com.example.android105class.api.Token
import com.example.android105class.dataClass.UserRequest
import com.example.android105class.databinding.FragmentLoginBinding
import com.example.android105class.di.MyApplication
//import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    private fun login(){
        binding.loginBtn.setOnClickListener {
            val username=binding.usernameEditTxt.text.toString()
            val password=binding.passwordEditTxt.text.toString()

            binding.loginBtn.isEnabled=false

            CoroutineScope(Dispatchers.IO).launch {
                val userRequest=UserRequest(username,password)
                val loginResponse=loginService.login(userRequest)

                if(loginResponse.isSuccessful){
                    val userInfo=loginResponse.body()
                    userInfo?.let {
                        Token.token=it.token
                    }

                    withContext(Dispatchers.Main){
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }else{
                    Toast.makeText(requireContext(),"Failed", Toast.LENGTH_SHORT).show()
                    binding.loginBtn.isEnabled=true
                }
            }
        }
    }
}