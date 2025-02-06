package com.example.roomdatabase.fragment.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.android.parcel.Parcelize
@Entity(tableName = "user_table")
data class TodoManager(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
   // var title:String,
    val firstName:String,
    val lastName:String,
    val age:Int,
    //var createdAt: Date
):Parcelable
