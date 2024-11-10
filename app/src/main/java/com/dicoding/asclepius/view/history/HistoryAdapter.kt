package com.dicoding.asclepius.view.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.HistoryEntity

class HistoryAdapter(private val onDeleteClick: (HistoryEntity) -> Unit) : ListAdapter<HistoryEntity, HistoryAdapter.CancersViewHolder>(DIFF_CALLBACK) {

    class CancersViewHolder(private val binding: ItemCancerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cancers: HistoryEntity, onDeleteClick: (HistoryEntity) -> Unit) {
            val title = cancers.title
            val image = cancers.mediaCover
            val dateTime = cancers.date
            val inference = cancers.inference

            binding.btnDelete.setOnClickListener {
                onDeleteClick(cancers)
            }

            binding.tvCancer.text = "Hasil : $title"
            binding.tvInferensi.text = "Waktu Inferensi : $inference ms"
            binding.tvTime.text = "Waktu Saat Di Analisis : $dateTime"

            LoadImage.load(
                itemView.context,
                binding.imgPicture,
                image, R.color.placeholder,
            )

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancersViewHolder {
        val view = ItemCancerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CancersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancersViewHolder, position: Int) {
        val cancerItem = getItem(position)
        if (cancerItem != null) {
            holder.bind(cancerItem, onDeleteClick)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HistoryEntity> =
            object : DiffUtil.ItemCallback<HistoryEntity>() {
                override fun areItemsTheSame(
                    oldItem: HistoryEntity,
                    newItem: HistoryEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: HistoryEntity,
                    newItem: HistoryEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}