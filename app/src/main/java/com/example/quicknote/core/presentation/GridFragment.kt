package com.example.quicknote.core.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.core.PostNoteAdapter
import com.example.quicknote.databinding.FragmentGridBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GridFragment : Fragment(R.layout.fragment_grid) {

    private val binding by viewBinding(FragmentGridBinding::bind)
    private val viewModel: GridViewModel by viewModels()
    private val noteAdapter = PostNoteAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotes()
        binding.recycler.apply {
            layoutManager = StaggeredGridLayoutManager(2, 1)
            adapter = noteAdapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        GridFragmentDirections.actionGridFragmentToNoteFragment(it.id)
                    )
                }
            }
        }
        viewModel.notesLiveData.observe(viewLifecycleOwner) { list ->
            noteAdapter.submitList(list)
        }

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.deleteNote(noteAdapter.getNoteId(viewHolder.adapterPosition))
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)

        binding.fab.setOnClickListener {
            viewModel.addNote("")
            findNavController().navigate(
                GridFragmentDirections.actionGridFragmentToNoteFragment(0)
            )
        }


        binding.toolbar.setOnMenuItemClickListener {
            findNavController().navigate(
                GridFragmentDirections.actionGridFragmentToSearchFragment()
            )
            true
        }


    }

}

