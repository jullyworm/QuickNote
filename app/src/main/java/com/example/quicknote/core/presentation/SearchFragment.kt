package com.example.quicknote.core.presentation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.core.FilterAdapter
import com.example.quicknote.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar


class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private val filterAdapter: FilterAdapter = FilterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes()

        val searchView = binding.search
        binding.recycler.apply {
            adapter = filterAdapter.apply {
                onItemClick = {
                    Snackbar.make(binding.root, it.text, Snackbar.LENGTH_LONG).show()
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToNoteFragment(it.id)
                    )
                }
            }
        }
        viewModel.notesLiveData.observe(viewLifecycleOwner) { list ->
            filterAdapter.submitList(list)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }


            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNotes(newText)
                return false
            }

        })
    }
}

