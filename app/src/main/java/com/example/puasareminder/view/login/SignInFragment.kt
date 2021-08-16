package com.example.puasareminder.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.puasareminder.MainActivity
import com.example.puasareminder.R
import com.example.puasareminder.databinding.FragmentSignInBinding
import com.example.puasareminder.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.base.BaseFragment

@AndroidEntryPoint
class SignInFragment : BaseFragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            loginProses()
        }

        binding.tvRegistrasi.setOnClickListener {
            setFragment(SignUpFragment())
        }
    }


    private fun setFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction!!.replace((activity as LoginActivity?) !!.binding.frameLogin.id, fragment)
        transaction.commit()
    }

    private fun loginProses() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.getLogin(email, password)
            .observe(viewLifecycleOwner, Observer {response ->
                when(response){
                    is ResponseState.Success ->{
                        progressDialog!!.dismiss()
                        goToMainActivity()
                    }
                    is ResponseState.Error ->{
                        progressDialog!!.dismiss()
                        Toast.makeText(requireContext(), "Password atau email salah", Toast.LENGTH_SHORT).show()
                    }
                    is ResponseState.Loading ->{
                        progressDialog!!.show()
                    }
                }
            })
    }

    private fun goToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}