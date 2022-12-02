package ru.test.applications.sqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.test.applications.sqlite.data.NoteRepository
import ru.test.applications.sqlite.databinding.ActivityNewNoteBinding
import ru.test.applications.sqlite.dto.Note

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.editTitle.requestFocus()
        binding.ok.setOnClickListener {
            val note = Note(
                id = NoteRepository.NEW_NOTE_ID,
                title = binding.editTitle.text.toString(),
                content = binding.editContent.text.toString()
            )
            onOkButtonClicked(note)

//            val intent = Intent(this, MainActivity::class.java)
//            navigateUpTo(intent)
        }
    }

    private fun onOkButtonClicked(note: Note) {
        val intent = Intent()

        if (note.content.isBlank()) {
            setResult(Activity.RESULT_CANCELED, intent)
        } else {
            intent.putExtra(NOTE_CONTENT_EXTRA_KEY, note)
            setResult(Activity.RESULT_OK, intent)
        }
    }

    companion object {
        const val NOTE_CONTENT_EXTRA_KEY = "noteContent"
    }

    object ResultContract : ActivityResultContract<Unit, Note?>() {
        override fun createIntent(context: Context, input: Unit): Intent =
            Intent(context, NewNoteActivity::class.java)

        override fun parseResult(resultCode: Int, intent: Intent?): Note? {
            if (resultCode != Activity.RESULT_OK) return null
            intent ?: return null

            return intent.getSerializableExtra(NOTE_CONTENT_EXTRA_KEY) as Note
        }
    }
}
