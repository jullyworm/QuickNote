package com.example.quicknote

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment: Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filterAdapter = FilterAdapter(Texts.texts)

        val searchView = binding.search
        binding.recycler.apply {
            adapter = filterAdapter.apply {
                onItemClick = {
                    Snackbar.make(binding.root, it.text, Snackbar.LENGTH_LONG).show()
                    findNavController().navigate(
                        resId = R.id.action_searchFragment_to_noteFragment,
                        args = bundleOf(
                            "text" to it.text,
                            "id" to it.id,
                        ),
                    )
                }
            }
        }

        fun filter(text: String) {
            val filtered = ArrayList<NoteData>()

            for (item in Texts.texts) {
                if (item.text.lowercase(Locale.getDefault())
                        .contains(text.lowercase(Locale.getDefault()))
                ) {

                    filtered.add(item)
                }
            }
            filterAdapter.filterList(filtered)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
    }
}

