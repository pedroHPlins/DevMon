package com.example.devmon.view.creatures

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devmon.R
import com.example.devmon.databinding.CreaturesListItemBinding
import com.example.devmon.model.domain.Creature

class CreatureListAdapter(
    private val items: List<Creature>,
    private val listener: (Creature) -> Unit,
) : RecyclerView.Adapter<CreatureListAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: CreaturesListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Creature, listener: (Creature) -> Unit) {
            binding.creature = item

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CreaturesListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listener)
    }

    override fun getItemCount() = items.count()
}