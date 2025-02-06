package com.example.roomdatabase.fragment.add

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.fragment.model.TodoManager
import com.example.roomdatabase.databinding.CustomRowBinding
import com.example.roomdatabase.fragment.list.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<TodoManager>()
    class MyViewHolder(var binding:CustomRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        var binding =CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

   return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.binding.numId.text = currentItem.id.toString()
        holder.binding.firstNameId.text=currentItem.firstName
        holder.binding.lastNameId.text=currentItem.lastName
        holder.binding.ageId.text= currentItem.age.toString()
        holder.binding.rowLayoutId.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(user:List<TodoManager>){
        this.userList = user
        Log.d("TAG", "sedsdtData: $user")
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
       return userList.size
    }
}