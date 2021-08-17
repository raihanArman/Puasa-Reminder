package com.example.puasareminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.puasareminder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.navHost))

        findNavController(R.id.navHost).addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.homeFragment,
                R.id.calendarFragment,
                R.id.keutamaanFragment,
                R.id.profileFragment ->
                    dataBinding.bottomNavigationView.visibility = View.VISIBLE
                else ->
                    dataBinding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}