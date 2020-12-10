package com.android.moviefinder.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.moviefinder.presentation.holder.BaseViewHolder
import com.android.moviefinder.presentation.typesfactory.TypesFactory
import com.android.moviefinder.presentation.vm.BaseVM

class RecyclerViewAdapter(
    private val items: Array<BaseVM>,
    private val typesFactory: TypesFactory
) : RecyclerView.Adapter<BaseViewHolder<BaseVM>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<BaseVM>, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseVM> {
        if (parent != null) {
            val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return typesFactory.holder(viewType, view) as BaseViewHolder<BaseVM>
        }
        throw RuntimeException("Parent is null")
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typesFactory)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}