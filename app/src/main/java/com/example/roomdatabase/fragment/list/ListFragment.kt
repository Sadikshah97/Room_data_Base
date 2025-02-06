package com.example.roomdatabase.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.view_model.TodoViewModel
import com.example.roomdatabase.databinding.FragmentListBinding
import com.example.roomdatabase.fragment.add.ListAdapter

class ListFragment : Fragment()
{
    lateinit var binding:FragmentListBinding
    private lateinit var mUserViewModel: TodoViewModel
    lateinit var adapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        adapter = ListAdapter()
        binding.recyclerView.adapter=adapter
        mUserViewModel=ViewModelProvider(this).get(TodoViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner , Observer {
            adapter.setData(it)
        })

        binding.floatingActonBUtton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(item.itemId==R.id.menu_delete){
          deteleAllUser()
      }
        return super.onOptionsItemSelected(item)
    }
    fun deteleAllUser(){
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _,_->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext()," All delete successfully  ",Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment  )
        }
        builder.setNegativeButton("No"){ _,_->

        }
        builder.setTitle("Delete All")
        builder.setMessage("Are you sure want  to delete All")
        builder.create().show()
    }



  }
