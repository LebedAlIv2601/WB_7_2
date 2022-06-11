package com.example.wb_7_2.presentation.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_7_2.R
import com.example.wb_7_2.databinding.MainRvItemBinding
import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import com.squareup.picasso.Picasso

class MainAdapter(private val onClick:(SuperHeroModelDomain) -> Unit):
    ListAdapter<SuperHeroModelDomain, MainAdapter.MainViewHolder>(DiffCallback()) {

    class MainViewHolder(private val binding: MainRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SuperHeroModelDomain){
            binding.apply {
                heroNameTextView.text = item.name
                Picasso.get().load(item.image)
                    .placeholder(R.drawable.superhero_placeholder)
                    .into(heroIconImageView)
            }
        }

        companion object{
            fun from(parent: ViewGroup): MainViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.main_rv_item,
                    parent, false)
                return MainViewHolder(MainRvItemBinding.bind(view))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val holder = MainViewHolder.from(parent)
        holder.itemView.setOnClickListener {
            if(holder.adapterPosition != RecyclerView.NO_POSITION){
                getItem(holder.adapterPosition)?.let(onClick)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback: DiffUtil.ItemCallback<SuperHeroModelDomain>() {
        override fun areItemsTheSame(
            oldItem: SuperHeroModelDomain,
            newItem: SuperHeroModelDomain
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: SuperHeroModelDomain,
            newItem: SuperHeroModelDomain
        ) = (oldItem.image == newItem.image) &&
                (oldItem.name == newItem.name)

    }
}