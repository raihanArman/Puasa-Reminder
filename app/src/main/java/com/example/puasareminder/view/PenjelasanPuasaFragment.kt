package com.example.puasareminder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.puasareminder.R
import com.example.puasareminder.databinding.FragmentPenjelasanPuasaBinding
import com.example.puasareminder.model.JenisPuasa
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PenjelasanPuasaFragment : Fragment() {

    private lateinit var binding: FragmentPenjelasanPuasaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_penjelasan_puasa, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jenis: JenisPuasa = requireArguments().getParcelable("jenis")!!

        binding.tvJenis.text = jenis.nama
        binding.tvKeutamaan.text = jenis.keutamaan

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }
}