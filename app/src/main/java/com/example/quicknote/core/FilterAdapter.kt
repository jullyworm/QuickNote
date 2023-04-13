package com.example.quicknote.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.core.domain.Note
import com.example.quicknote.databinding.NoteContentCardBinding
import com.example.quicknote.inflate
import java.util.*
import kotlin.collections.ArrayList

class FilterAdapter() : ListAdapter<Note, FilterAdapter.ViewHolder>(PostNoteAdapter.DiffCallback) {
   // private var dataList: List<Note>
    var onItemClick: (Note) -> Unit = {}

  /*  init {
        this.dataList = dataList
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
            onItemClick = onItemClick,
        )
    }

  /*  override fun getItemCount(): Int {
        return dataList.size
    }*/


  /*  @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: MutableList<Note>) {
        dataList = filterList
        notifyDataSetChanged()
    }*/

    /*fun filter(text: String) {
        val filtered = ArrayList<Note>()
        for (item in dataList) {
            if (item.text.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                filtered.add(item)
            }
        }
        filterList(filtered)
    }*/

    override fun onBindViewHolder(holder: FilterAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        parent: ViewGroup,
        private val onItemClick: (Note) -> Unit
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.note_content_card),
    ) {
        private val binding by viewBinding(NoteContentCardBinding::bind)

        fun bind(item: Note) {
            binding.text.text = item.text
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
    object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }
}