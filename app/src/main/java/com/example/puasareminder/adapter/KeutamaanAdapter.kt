package com.example.puasareminder.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.puasareminder.R
import com.example.puasareminder.databinding.ItemJenisBinding
import com.example.puasareminder.databinding.ItemPuasaBinding
import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa

class KeutamaanAdapter(val showKeutamaan: (JenisPuasa) -> Unit): RecyclerView.Adapter<KeutamaanAdapter.ViewHolder>(){

    private val listPuasa = ArrayList<JenisPuasa>()

    fun setListPuasa(listPuasa: List<JenisPuasa>){
        this.listPuasa.clear()
        this.listPuasa.addAll(listPuasa)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemJenisBinding = DataBindingUtil.inflate(inflater, R.layout.item_jenis, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val puasa = listPuasa[position]
        holder.bind(puasa)
    }

    override fun getItemCount(): Int {
        return listPuasa.size
    }

    inner class ViewHolder(val binding: ItemJenisBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(puasa: JenisPuasa){
            with(binding){
                tvPuasa.text = puasa.nama
            }
            itemView.setOnClickListener {
                showKeutamaan(puasa)
            }
        }
    }

}