package jp.hika019.notepad

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val TAG = "MainViewModel"

    fun addButtonTapped(){
        Log.d(TAG, "addButtonTapped")
    }
}