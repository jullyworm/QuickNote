package com.example.quicknote

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.databinding.NoteContentCardBinding


class PostNoteAdapter: ListAdapter<NoteData, PostNoteAdapter.ViewHolder>(DiffCallback){

    var onItemClick: (NoteData)->Unit={}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
            onItemClick = onItemClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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

    object DiffCallback : DiffUtil.ItemCallback<NoteData>(){
        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}