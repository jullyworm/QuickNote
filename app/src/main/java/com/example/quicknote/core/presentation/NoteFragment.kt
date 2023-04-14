package com.example.quicknote.core.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {
    private val binding by viewBinding(FragmentNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = requireArguments().getInt("id")

        if (id == 0) {
            viewModel.getAddedNote()
        }
        else viewModel.getNote(id)

        viewModel.noteLiveData.observe(viewLifecycleOwner) { note ->
            binding.editText.text.append(note.text)
        }

        binding.editText.doAfterTextChanged { text ->
            viewModel.noteLiveData.observe(viewLifecycleOwner) { note ->
                viewModel.saveNote(note.id, text.toString())
            }
        }
    }
}