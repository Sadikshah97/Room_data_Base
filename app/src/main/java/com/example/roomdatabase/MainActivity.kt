package com.example.roomdatabase

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
      }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragment)
        return navController.navigateUp()||super.onSupportNavigateUp()

    }
}