package com.example.traveloka.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveloka.databinding.ItemRowPromoBinding
import com.example.traveloka.model.Promo
import com.example.traveloka.utils.hide

class PromoListAdapter(
    private val onClickItem:(promo: Promo) -> Unit
): ListAdapter<Promo, PromoListAdapter.PromoListViewHolder>(DiffCallBack){

    private lateinit var ctx: Context

    inner class PromoListViewHolder(binding: ItemRowPromoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val root = binding.root
        private val ivPromo = binding.ivPromo
        private val tvTitlePromo = binding.tvTitlePromo
        private val tvPeriod = binding.tvPeriod

        fun bind(promo: Promo) {
            ivPromo.setImageResource(promo.image)
            tvTitlePromo.text = ctx.getString(promo.title)
            if (ctx.getString(promo.period).isBlank()) {
                tvPeriod.hide()
            } else {
                tvPeriod.text = ctx.getString(promo.period)
            }
        }
    }
    private companion object DiffCallBack: DiffUtil.ItemCallback<Promo>(){
        override fun areItemsTheSame(oldItem: Promo, newItem: Promo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Promo, newItem: Promo): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoListViewHolder {
        ctx = parent.context
        val binding = ItemRowPromoBinding.inflate(LayoutInflater.from(ctx), parent, false)
        return PromoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}