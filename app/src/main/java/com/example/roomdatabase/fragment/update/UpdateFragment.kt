package com.example.roomdatabase.fragment.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentUpdateBinding
import com.example.roomdatabase.fragment.model.TodoManager
import com.example.roomdatabase.view_model.TodoViewModel

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var binding:FragmentUpdateBinding
    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        viewModel =ViewModelProvider(this).get(TodoViewModel::class.java)
        binding.updateFirstNameEt.setText(args.currentUser.firstName.toString())
        binding.updateLastNameEt.setText(args.currentUser.lastName.toString())
        binding.updateAgeEt.setText(args.currentUser.age.toString())
        binding.updateBtn.setOnClickListener {
              updateItem()
        }
        setHasOptionsMenu(true)
        return binding.root
    }
   /* fun updateItem(){
        val firsName=binding.updateFirstNameEt.text.toString()
        val lastName=binding.updateLastNameEt.text.toString()
        val age = Integer.parseInt(binding.updateAgeEt.text.toString())
         if(inputCheck(firsName,lastName,binding.updateAgeEt.text)){
                   var updateUser = TodoManager(args.currentUser.id,firsName,lastName,age)
                    viewModel.updateUser(updateUser)
                 Toast.makeText(requireContext(),"Updated Successfully",Toast.LENGTH_SHORT).show()
             findNavController().navigate(R.id.action_updateFragment_to_listFragment)
         }else{
             Toast.makeText(requireContext(),"Not Updated Successfully",Toast.LENGTH_SHORT).show()

         }
    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
*/
   private fun updateItem() {
       val firstName = binding.updateFirstNameEt.text.toString().trim()
       val lastName = binding.updateLastNameEt.text.toString().trim()
       val ageText = binding.updateAgeEt.text.toString().trim()

       if (inputCheck(firstName, lastName, ageText)) {
           val age = ageText.toIntOrNull()

           if (age == null) {
               Toast.makeText(requireContext(), "Please enter a valid age.", Toast.LENGTH_LONG).show()
               return
           }

           // Create Updated User Object
           val updatedUser = TodoManager(args.currentUser.id, firstName, lastName, age)

           // Update Data in Database
           viewModel.updateUser(updatedUser)
           Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()

           // Navigate Back
           findNavController().navigate(R.id.action_updateFragment_to_listFragment)
       } else {
           Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
       }
   }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(item.itemId==R.id.menu_delete){
          deleteUser()
      }
        return super.onOptionsItemSelected(item)

    }
    private fun deleteUser(){
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _,_->
            viewModel.deleteuser(args.currentUser)
            Toast.makeText(requireContext(),"successfully remove ${args.currentUser.firstName} ",Toast.LENGTH_SHORT).show()
           findNavController().navigate(R.id.action_updateFragment_to_listFragment  )
        }
        builder.setNegativeButton("No"){ _,_->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure want  to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }


}