package com.example.puasareminder.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.puasareminder.R
import com.example.puasareminder.databinding.ItemPuasaBinding
import com.example.puasareminder.model.Puasa

class PuasaAdapter: RecyclerView.Adapter<PuasaAdapter.ViewHolder>(){

    private val listPuasa = ArrayList<Puasa>()

    fun setListPuasa(listPuasa: List<Puasa>){
        this.listPuasa.clear()
        this.listPuasa.addAll(listPuasa)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPuasaBinding = DataBindingUtil.inflate(inflater, R.layout.item_puasa, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val puasa = listPuasa[position]
        holder.bind(puasa)
    }

    override fun getItemCount(): Int {
        return listPuasa.size
    }

    inner class ViewHolder(val binding: ItemPuasaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(puasa: Puasa){
            with(binding){
                val date = DateFormat.format("dd MMM yyyy", puasa.tanggal).toString()
                tvTanggal.text = date
                tvPuasa.text = puasa.jenisPuasa.nama
            }
        }
    }

}