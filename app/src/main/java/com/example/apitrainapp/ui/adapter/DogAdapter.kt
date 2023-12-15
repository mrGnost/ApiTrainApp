package com.example.apitrainapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.apitrainapp.R
import com.example.apitrainapp.data.model.DogSample

class DogAdapter(private val dogs: MutableList<DogSample>) : Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DogViewHolder(inflater.inflate(R.layout.holder_dog, parent, false))
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogs[position])
    }

    fun addDog(dog: DogSample) {
        dogs.add(dog)
        notifyItemInserted(dogs.size - 1)
    }
}

class DogViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(dog: DogSample) {
        itemView.findViewById<ImageView>(R.id.dog_image).load(dog.message)
    }
}