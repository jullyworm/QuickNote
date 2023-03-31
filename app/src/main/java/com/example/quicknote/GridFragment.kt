package com.example.quicknote

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.databinding.FragmentGridBinding
import com.google.android.material.snackbar.Snackbar


class GridFragment : Fragment(R.layout.fragment_grid) {

    private val binding by viewBinding(FragmentGridBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postNoteAdapter = PostNoteAdapter()

        val sGridLM: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)

        binding.recycler.apply {
            layoutManager = sGridLM
            adapter = postNoteAdapter.apply {
                onItemClick = {
                    Snackbar.make(binding.root, it.text, Snackbar.LENGTH_LONG).show()
                    findNavController().navigate(
                        resId = R.id.action_gridFragment_to_noteFragment,
                        args = bundleOf(
                            "text" to it.text,
                            "id" to it.id,
                        ),
                    )
                }
                submitList(Texts.texts)
            }
        }

        binding.fab.setOnClickListener{
            val i = Texts.texts.size
            Texts.texts.add(NoteData(i, ""))
            findNavController().navigate(
                resId = R.id.action_gridFragment_to_noteFragment,
                args = bundleOf(
                    "text" to "",
                    "id" to i,
                ),
            )
        }
        binding.toolbar.setOnMenuItemClickListener{
            findNavController().navigate(
                resId = R.id.action_gridFragment_to_searchFragment,
            )
            true
        }
    }
}

