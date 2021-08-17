package com.example.puasareminder.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.puasareminder.R
import com.example.puasareminder.databinding.FragmentCalendarBinding
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.util.EventDecorator
import com.example.puasareminder.viewmodel.HomeViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.model.response.ResponseState
import java.lang.Exception
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarPuasa.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE

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
    }

    private fun setData(data: List<Puasa>) {
        data.forEach {
            val puasaDrawable = getPuasaDrawable(it.jenisPuasa.nama)
            val dd = removeZero(DateFormat.format("dd", it.tanggal).toString())
            val mm = removeZero(DateFormat.format("MM", it.tanggal).toString())
            val yyyy = DateFormat.format("yyyy", it.tanggal).toString().toInt()
            val listCalendar = mutableListOf<CalendarDay>()
            val c1 = CalendarDay.from(yyyy, mm-1, dd)

            listCalendar.add(c1)
            binding.calendarPuasa.addDecorators(EventDecorator(puasaDrawable, listCalendar))
        }
    }

    private fun getPuasaDrawable(nama: String): Drawable {
        return when(nama){
            "Puasa Syawal" ->
                resources.getDrawable(R.drawable.bg_selected_day2)
            else ->
                resources.getDrawable(R.drawable.bg_selected_day)
        }
    }

    fun removeZero(date: String): Int{
        return try{
            val dd = date.get(0)
            if(dd == '0'){
                date.get(1).toString().toInt()
            }
            date.toInt()
        }catch (e: Exception){
            0
        }
    }

}