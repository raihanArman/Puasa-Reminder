package com.example.puasareminder.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.puasareminder.MainActivity
import com.example.puasareminder.R
import com.example.puasareminder.databinding.FragmentProfileBinding
import com.example.puasareminder.model.Users
import com.example.puasareminder.view.login.LoginActivity
import com.example.puasareminder.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.model.response.ResponseState

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserve()

        binding.btnLogout.setOnClickListener {
            logoutProses()
        }

    }

    private fun logoutProses() {
        viewModel.logoutUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    if(response.data){
                        revokeData()
                    }
                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun revokeData() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setupObserve() {
        viewModel.getUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setData(response.data)
                }
                is ResponseState.Error ->{

                }
            }
        }
    }

    private fun setData(data: Users) {
        with(binding){
            tvUser.text = data.name
            tvEmail.text = data.email
        }
    }
}