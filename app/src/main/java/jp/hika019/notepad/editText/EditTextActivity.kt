package jp.hika019.notepad.editText

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import jp.hika019.notepad.R
import jp.hika019.notepad.databinding.ActivityEditTextBinding
import jp.hika019.notepad.txtData


class EditTextActivity: AppCompatActivity() {

    private val TAG = "EditTextActivity"
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


        viewModel.editActivityFinish.observe(this, Observer {
            if (viewModel.editActivityFinish.value == true){
                Log.d(TAG, txtData.value.toString())
                finish()
            }
        })



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}