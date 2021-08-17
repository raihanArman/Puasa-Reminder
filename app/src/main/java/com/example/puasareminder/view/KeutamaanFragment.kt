package com.example.puasareminder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puasareminder.R
import com.example.puasareminder.adapter.KeutamaanAdapter
import com.example.puasareminder.databinding.FragmentKeutamaanBinding
import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.model.response.ResponseState
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class KeutamaanFragment : Fragment() {

    private lateinit var binding: FragmentKeutamaanBinding
    private val adapter: KeutamaanAdapter by lazy {
        KeutamaanAdapter{
            showKeutamaan(it)
        }
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_keutamaan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObserve()

    }

    private fun setupObserve() {
        viewModel.getJenisPuasa().observe(viewLifecycleOwner){response ->
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

    private fun setData(data: List<JenisPuasa>) {
        adapter.setListPuasa(data)
    }

    private fun setupAdapter() {
        with(binding){
            rvJenis.layoutManager = LinearLayoutManager(requireContext())
            rvJenis.adapter = adapter
        }
    }

    private fun showKeutamaan(puasa: JenisPuasa){
        val bundle = bundleOf("jenis" to  puasa)
        findNavController().navigate(R.id.penjelasanPuasaFragment, bundle)
    }
}