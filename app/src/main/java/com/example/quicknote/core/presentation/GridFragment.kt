package com.example.quicknote.core.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.core.PostNoteAdapter
import com.example.quicknote.databinding.FragmentGridBinding


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
                        resId = R.id.action_gridFragment_to_noteFragment,
                        args = bundleOf(
                            "id" to it.id,
                        ),
                    )
                }
            }
        }
        viewModel.notesLiveData.observe(viewLifecycleOwner) { list ->
            noteAdapter.submitList(list)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(
                resId = R.id.action_gridFragment_to_noteFragment,
            )
        }
        binding.toolbar.setOnMenuItemClickListener {
            findNavController().navigate(
                resId = R.id.action_gridFragment_to_searchFragment,
            )
            true
        }
    }
}
