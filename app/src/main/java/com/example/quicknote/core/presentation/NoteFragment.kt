package com.example.quicknote.core.presentation

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            viewModel.setUri(uri)
            binding.imageCard.cancelButton.visibility = View.VISIBLE
            binding.imageCard.image.setImageURI(uri)
        }
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = requireArguments().getInt("id")

        if (id == 0) {
            viewModel.getAddedNote()
        } else viewModel.getNote(id)

        viewModel.noteLiveData.observe(viewLifecycleOwner) { note ->
            binding.textInput.setText(note.text)
            val imgBitmap = BitmapFactory.decodeFile(note.imagePath)
            binding.imageCard.image.setImageBitmap(imgBitmap)
            if (imgBitmap != null) {
                binding.imageCard.cancelButton.visibility = View.VISIBLE
            } else {
                binding.imageCard.cancelButton.visibility = View.INVISIBLE
            }
        }
        viewModel.imageLiveData.observe(viewLifecycleOwner) { uri ->
            if (uri != null) {
                viewModel.saveImage(id, uri)
            }
        }


        binding.textInput.doAfterTextChanged { text ->
            viewModel.noteLiveData.observe(viewLifecycleOwner) { note ->
                viewModel.saveNote(note.id, text.toString())
            }
        }

        binding.chip.setOnClickListener {
            imagePickerLauncher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }

        binding.imageCard.cancelButton.setOnClickListener {
            viewModel.deleteImage()
            viewModel.updateNote()
        }
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setNavigationIconTint(binding.textInput.textColors.defaultColor)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}