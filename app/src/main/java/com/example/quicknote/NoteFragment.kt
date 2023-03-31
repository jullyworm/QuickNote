package com.example.quicknote

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.databinding.FragmentNoteBinding

class NoteFragment: Fragment(R.layout.fragment_note) {
    private val binding by viewBinding(FragmentNoteBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : Bundle = requireArguments()
        binding.editText.text.append(args.getString("text"))

        val editText : EditText = binding.editText

        editText.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Texts.texts[args.getInt("id")] = NoteData(args.getInt("id"), s.toString())
                if(Texts.texts[args.getInt("id")].text == "") Texts.texts.remove(Texts.texts[args.getInt("id")])
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}