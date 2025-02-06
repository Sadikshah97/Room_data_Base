package com.example.roomdatabase.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.fragment.model.TodoManager
import com.example.roomdatabase.view_model.TodoViewModel
import com.example.roomdatabase.databinding.FragmentAddBinding

class AddFragment : Fragment() {
   lateinit var binding:FragmentAddBinding
   lateinit var viewModel: TodoViewModel


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentAddBinding.inflate(inflater, container, false)
              viewModel= ViewModelProvider(this)[TodoViewModel::class.java]
       binding.addBtn.setOnClickListener {
           insertDataToDatabase()
       }

            return binding.root
        }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if(inputCheck(firstName, lastName, age)){
            // Create User Object
            val user = TodoManager(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add Data to Database
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}