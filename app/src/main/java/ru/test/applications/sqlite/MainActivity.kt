package ru.test.applications.sqlite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.test.applications.sqlite.databinding.ActivityMainBinding
import ru.test.applications.sqlite.databinding.ActivityNoteBinding
import ru.test.applications.sqlite.viewModel.NotesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityLauncher = registerForActivityResult(
            NewNoteActivity.ResultContract
        ) { note ->
            note?.let(viewModel::onCreateNewNote)
        }

        binding.addNote.setOnClickListener {

            // Очистка контейнера от view
            binding.container.removeAllViews()

            activityLauncher.launch(Unit)
        }

        viewModel.data.observe(this) { notes ->
            notes.map { note ->
                ActivityNoteBinding.inflate(
                    layoutInflater, binding.container, false
                ).apply {
                    title.text = note.title
                    content.text = note.content
                }.root
            }.forEach { noteView ->
                binding.container.addView(noteView)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.filterData.observe(this) { notes ->
            notes.map { note ->
                ActivityNoteBinding.inflate(
                    layoutInflater, binding.container, false
                ).apply {
                    title.text = note.title
                    content.text = note.content
                }.root
            }.forEach { noteView ->
                binding.container.addView(noteView)
            }
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(textSearch: CharSequence?, start: Int, count: Int, after: Int) {
                binding.container.removeAllViews()
            }

            override fun onTextChanged(textSearch: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onSearch(textSearch.toString())
            }

            override fun afterTextChanged(textSearch: Editable?) {
            }
        })
    }
}