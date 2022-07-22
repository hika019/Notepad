package jp.hika019.notepad.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.hika019.notepad.addText.AddTextActivity

class MainViewModel: ViewModel() {
    private val TAG = "MainViewModel"


    fun addButtonTapped(context: Context){
        Log.d(TAG, "addButtonTapped")
        val intent = Intent(context, AddTextActivity::class.java)
        context.startActivity(intent)
    }
}