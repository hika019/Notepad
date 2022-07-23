package jp.hika019.notepad.editText

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.hika019.notepad.R
import jp.hika019.notepad.databinding.ActivityEditTextBinding


class EditTextActivity: AppCompatActivity() {

    private val viewModel: EditTextViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityEditTextBinding>(
            this, R.layout.activity_edit_text
        )

        binding.editViewModel = viewModel
        binding.lifecycleOwner = this

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}