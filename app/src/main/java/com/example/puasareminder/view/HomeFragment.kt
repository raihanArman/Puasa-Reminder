package com.example.puasareminder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puasareminder.R
import com.example.puasareminder.adapter.PuasaAdapter
import com.example.puasareminder.databinding.FragmentHomeBinding
import com.example.puasareminder.datastore.UserDataStore
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import com.example.puasareminder.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.model.response.ResponseState
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var userDataStore: UserDataStore

    private val adapter: PuasaAdapter by lazy {
        PuasaAdapter{
            insertPuasa(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getPuasa().observe(viewLifecycleOwner){response ->
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
        viewModel.getUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setDataUser(response.data)
                }
                is ResponseState.Error ->{

                }
            }
        }
    }

    private fun setDataUser(data: Users) {
        binding.tvUser.text = "Assalamu alaikum ${data.name}"
    }

    private fun setData(data: List<Puasa>) {
        userDataStore.getIdUserFlow.asLiveData().observe(viewLifecycleOwner){idUser ->
            val dataDatabase = viewModel.getPuasaByUser(idUser)
            val dataSet = mutableListOf<Puasa>()
            data.forEach {dataRemote->
                val puasa = Puasa(
                    dataRemote.id,
                    dataRemote.idJenisPuasa,
                    dataRemote.tanggal,
                    dataRemote.jenisPuasa)
                if(viewModel.checkPuasaExists(dataRemote.id)){
                    puasa.status = "1"
                }else{
                    puasa.status = "0"
                }
                dataSet.add(puasa)
            }

            adapter.setListPuasa(dataSet)
        }
    }

    private fun setupAdapter() {
        with(binding){
            rvPuasa.layoutManager = LinearLayoutManager(requireContext())
            rvPuasa.adapter = adapter
        }
    }

    private fun insertPuasa(puasa: Puasa){
        userDataStore.getIdUserFlow.asLiveData().observe(viewLifecycleOwner){idUser ->
            val puasaEntity = PuasaEntity(
                0,
                idUser,
                puasa.id,
                puasa
            )
            viewModel.insertPuasa(puasaEntity)
        }
    }

}