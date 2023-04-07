package com.example.quicknote.core.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentNoteBinding

class NoteFragment: Fragment(R.layout.fragment_note) {
    private val binding by viewBinding(FragmentNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels()
    private val gridViewModel : GridViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : Bundle = requireArguments()
        val id : String? =  args.getString("id")

        args.getString("id")?.let { viewModel.getNote(it) }
        if( id == null) {viewModel.addNote("")}

        val editText : EditText = binding.editText

        viewModel.noteLiveData.observe(viewLifecycleOwner){ note ->
            binding.editText.text.append(note.text)
        }

        editText.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //if( id == null) {viewModel.addNote(s.toString())}
                viewModel.noteLiveData.observe(viewLifecycleOwner){ note ->
                    gridViewModel.saveNote(note.id, s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}