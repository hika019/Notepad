package jp.hika019.notepad.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import jp.hika019.notepad.editText.EditTextActivity

class MainViewModel: ViewModel() {
    private val TAG = "MainViewModel"


    fun addButtonTapped(context: Context){
        Log.d(TAG, "addButtonTapped")
        val intent = Intent(context, EditTextActivity::class.java)
        context.startActivity(intent)
    }
}