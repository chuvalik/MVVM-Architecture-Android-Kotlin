package com.example.astronomicalphotooftheday.presentation.apod_random_items_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astronomicalphotooftheday.databinding.AdapterApodItemBinding
import com.example.astronomicalphotooftheday.domain.model.Apod

class ApodRandomAdapter(
    private var onAdd: (Apod) -> Unit
) : ListAdapter<Apod, ApodRandomAdapter.ApodItemsViewHolder>(ApodRandomDiffCallback) {

    class ApodItemsViewHolder(
        val binding: AdapterApodItemBinding,
        private var onAdd: (Apod) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apod: Apod) {
            binding.apply {
                btnDetail.setOnClickListener {
                    // Extend card with detail info
                    if (extendedLinearLayout.visibility == View.GONE)
                        extendedLinearLayout.visibility = View.VISIBLE
                    else
                        extendedLinearLayout.visibility = View.GONE
                }
                btnAddFavorites.setOnClickListener {
                    onAdd(apod)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodItemsViewHolder(
            AdapterApodItemBinding.inflate(layoutInflater, parent, false),
            onAdd
        )
    }

    override fun onBindViewHolder(holder: ApodItemsViewHolder, position: Int) {
        val apodItem = getItem(position)
        holder.binding.tvTitleItem.text = apodItem.title
        holder.binding.imgApodItem.load(apodItem.url)
        holder.binding.tvContentItem.text = apodItem.explanation
        holder.bind(apodItem)
    }

    companion object ApodRandomDiffCallback : DiffUtil.ItemCallback<Apod>() {
        override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem.date == newItem.date
        }

    }
}