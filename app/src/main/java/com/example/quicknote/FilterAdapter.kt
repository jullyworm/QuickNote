package com.example.quicknote

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.databinding.NoteContentCardBinding

class FilterAdapter (dataList: MutableList<NoteData>) : RecyclerView.Adapter< FilterAdapter.ViewHolder>(){
    private var dataList: MutableList<NoteData>
    var onItemClick: (NoteData)->Unit={}

    init {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
            onItemClick = onItemClick,
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: MutableList<NoteData>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    class ViewHolder(
        parent: ViewGroup,
        private val onItemClick:(NoteData)->Unit
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.note_content_card),
    ){
        private val binding by viewBinding(NoteContentCardBinding::bind)

        fun bind(item: NoteData) {
            binding.text.text = item.text
            binding.root.setOnClickListener{
                onItemClick(item)
            }
        }
    }
}